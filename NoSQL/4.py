import csv
import boto3

s3 = boto3.resource('s3', aws_access_key_id='AKIAYT7ZQ7LZR6FOWE4U', aws_secret_access_key='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')
dyndb = boto3.resource('dynamodb', region_name='us-west-2', aws_access_key_id='AKIAYT7ZQ7LZR6FOWE4U', aws_secret_access_key='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')
table = dyndb.Table('DataTable2')
bucketName = 'datatesting-one'

response = table.get_item(Key={'PartitionKey': 'experiment3','RowKey': '3'})
item = response['Item']
print(item)