import json
import boto3
import uuid
from datetime import datetime
 
# Initialize DynamoDB resource
dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('UserSubmissions')
 
def lambda_handler(event, context):
    # Common CORS headers
    cors_headers = {
        "Access-Control-Allow-Origin": "*",  # or "http://13.127.181.157"
        "Access-Control-Allow-Methods": "OPTIONS,POST",
        "Access-Control-Allow-Headers": "Content-Type,Authorization"
    }
 
    # --- Handle CORS preflight request ---
    if event.get("httpMethod") == "OPTIONS":
        return {
            "statusCode": 200,
            "headers": cors_headers,
            "body": json.dumps({"message": "CORS preflight OK"})
        }
 
    # --- Handle POST request ---
    if event.get("httpMethod") != "POST":
        return {
            "statusCode": 405,  # Method Not Allowed
            "headers": cors_headers,
            "body": json.dumps({"error": "Method not allowed"})
        }
 
    # Parse JSON body safely
    try:
        body = json.loads(event.get("body", "{}"))
        name = body["name"]
        email = body["email"]
        message = body["message"]
    except (KeyError, json.JSONDecodeError):
        return {
            "statusCode": 400,
            "headers": cors_headers,
            "body": json.dumps({"error": "Invalid input"})
        }
 
    # Prepare submission data
    submission_id = str(uuid.uuid4())
    submission_date = datetime.utcnow().isoformat()
    status = "NEW"
 
    # Save to DynamoDB
    try:
        table.put_item(
            Item={
                "submissionId": submission_id,
                "name": name,
                "email": email,
                "message": message,
                "submissionDate": submission_date,
                "status": status
            }
        )
    except Exception as e:
        return {
            "statusCode": 500,
            "headers": cors_headers,
            "body": json.dumps({"error": f"Database error: {str(e)}"})
        }
 
    # Success response
    return {
        "statusCode": 200,
        "headers": cors_headers,
        "body": json.dumps({
            "message": "Submission successful",
            "submissionId": submission_id
        })
    }
 