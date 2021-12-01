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
	public static class mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		private final IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			StringTokenizer itr = new StringTokenizer(value.toString());
			while(itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}

		}
	}

	//Reducer Class
	public static class reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		public IntWritable result = new IntWritable();
		public HashMap<String, Integer> hm;

		@Override
		public void setup(Context context) throws IOException, InterruptedException {
			hm = new HashMap<String, Integer>();
		}

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			
			int sum = 0;
			for(IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			//context.write(key, result);

			String keyStr = key.toString();
			/*if(hm.containsKey(key.toString())) {
				hm.put(keyStr, hm.get(keyStr) + 1);
			} else {
				hm.put(keyStr, 1);
			}*/
			hm.put(keyStr, sum);

		}

		@Override
		public void cleanup(Context context) throws IOException, InterruptedException {

			Comparator<String> comp = new Comparator<String>() {
				public int compare(String sThis, String sOther) {
					return Integer.compare(hm.get(sThis), hm.get(sOther));
				}
			};

			java.util.PriorityQueue<String> topFive = new java.util.PriorityQueue<String>(5, comp);
			for(String k : hm.keySet()) {
				if(topFive.size() < 5) {
					topFive.add(k);
				} else if(hm.get(topFive.peek()) < hm.get(k)) {
					topFive.poll();
					topFive.add(k);
				}
			}

			for(int i = 0; i < 5; i++) {
				String s = topFive.poll();
				context.write(new Text(s), new IntWritable(hm.get(s)));
			}

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
		job.setJobName("top_10_words");

		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}