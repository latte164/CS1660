import csv
import boto3

s3 = boto3.resource('s3', aws_access_key_id='access_key', aws_secret_access_key='secret_key')
dyndb = boto3.resource('dynamodb', region_name='us-west-2', aws_access_key_id='access_key', aws_secret_access_key='secret_key')
table = dyndb.Table('DataTable2')
bucketName = 'datatesting-one'

with open('experiments.csv', 'r') as csvfile:
	csvf = csv.reader(csvfile, delimiter=',', quotechar='|')
	print(csvf)
	for item in csvf:
		print(item)
		body = open(str(item[3]), 'rb')
		s3.Object(bucketName, item[3]).put(Body=body)
		md = s3.Object(bucketName, item[3]).Acl().put(ACL='public-read')

		url = "https://s3-us-west-2.amazonaws.com/"+bucketName+"/"+item[3]
		metadata_item = {'PartitionKey': item[0], 'RowKey': item[1], 'description' : item[4], 'date' : item[2], 'url':url}
		
		try:
			table.put_item(Item=metadata_item)
		except:
			print("item may already be there or another failure")