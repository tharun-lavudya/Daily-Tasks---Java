import json
import boto3
 
dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('UserSubmissions')
 
def lambda_handler(event, context):
    # Get query string parameters (email optional)
    params = event.get('queryStringParameters') or {}
    email = params.get('email')
 
    try:
        if email:
            # Query by email (assumes GSI or scan filter)
            # Since no GSI specified, using scan with filter
            response = table.scan(
                FilterExpression='email = :email_val',
                ExpressionAttributeValues={':email_val': email}
            )
            items = response.get('Items', [])
        else:
            # Get all items (scan)
            response = table.scan()
            items = response.get('Items', [])
 
    except Exception as e:
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,GET'
            },
            'body': json.dumps({'error': str(e)})
        }
 
    return {
        'statusCode': 200,
        'headers': {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Methods': 'OPTIONS,GET'
        },
        'body': json.dumps(items)
    }
 