package hadoop;

import java.lang.*;
import java.util.*;
import java.io.*;
import org.apache.hadoop.mapreduce.*; 
import org.apache.hadoop.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class driver {

	//Mapper Class
	public class mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		private static final int MISSING = 9999;

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String line = value.toString();
			String date = line.substring(15,23);
			int airTemp;
			if(line.charAt(87) == '+') {
				airTemp = Integer.parseInt(line.substring(88,92));
			} else {
				airTemp = Integer.parseInt(line.substring(87,92));
			}
			String quality = line.substring(92,93);

			if(airTemp != MISSING && quality.matches("[01459]")) {
				context.write(new Text(date), new IntWritable(airTemp));
			}

		}
	}

	//Reducer Class
	public class reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int max = Integer.MIN_VALUE;
			for (IntWritable value : values) {
				max = Math.max(max, value.get());
			}
			context.write(key, new IntWritable(max));
		}
	}

	//Driver Function
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: driver <input path> <output path>");
			System.exit(-1);
		}

		Job job = new Job();
		job.setJarByClass(driver.class);
		job.setJobName("max_temps");

		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}