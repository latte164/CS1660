import boto3

bucketName = 'datatesting-one'

s3 = boto3.resource('s3', aws_access_key_id='AKIAYT7ZQ7LZR6FOWE4U', aws_secret_access_key='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')

try:
	s3.create_bucket(Bucket=bucketName, CreateBucketConfiguration={'LocationConstraint': 'us-west-2'})
except Exception as e:
	print (e)

bucket = s3.Bucket(bucketName)
bucket.Acl().put(ACL='public-read')

s3.Object(bucketName, 'test.jpg').put(Body=open('test.jpg', 'rb'))
s3.Object(bucketName, 'test.jpg').Acl().put(ACL='public-read')