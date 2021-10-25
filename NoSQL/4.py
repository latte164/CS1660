import csv
import boto3

s3 = boto3.resource('s3', aws_access_key_id='access_key', aws_secret_access_key='secret_key')
dyndb = boto3.resource('dynamodb', region_name='us-west-2', aws_access_key_id='access_key', aws_secret_access_key='secret_key')
table = dyndb.Table('DataTable2')
bucketName = 'datatesting-one'

response = table.get_item(Key={'PartitionKey': 'experiment3','RowKey': '3'})
item = response['Item']
print(item)