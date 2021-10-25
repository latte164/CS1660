import boto3

dyndb = boto3.resource('dynamodb', region_name='us-west-2', aws_access_key_id='AKIAYT7ZQ7LZR6FOWE4U', aws_secret_access_key='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')

try:
 table = dyndb.create_table(
 TableName='DataTable2',
 KeySchema=[
 {
 'AttributeName': 'PartitionKey',
 'KeyType': 'HASH'
 },
 {
 'AttributeName': 'RowKey',
 'KeyType': 'RANGE'
 }
 ],
 AttributeDefinitions=[
 {
 'AttributeName': 'PartitionKey',
 'AttributeType': 'S'
 },
 {
 'AttributeName': 'RowKey',
 'AttributeType': 'S'
 },
 ],
 ProvisionedThroughput={
 'ReadCapacityUnits': 5,
 'WriteCapacityUnits': 5
 }
 )
except Exception as e:
 print (e)
 #if there is an exception, the table may already exist. if so...
 table = dyndb.Table("DataTable2")
#wait for the table to be created
table.meta.client.get_waiter('table_exists').wait(TableName='DataTable2')
print(table.item_count)