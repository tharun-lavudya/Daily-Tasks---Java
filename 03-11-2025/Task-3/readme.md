Text File Processor Lambda

A Java 17 AWS Lambda function that automatically processes .txt files uploaded to an S3 bucket.
It counts lines, words, and characters, extracts a 100-character preview, and stores the results in DynamoDB.

Project Overview

S3 Bucket: Receives .txt file uploads and triggers Lambda.

Lambda Function: Processes the file:

Counts lines, words, characters

Extracts the first 100 characters as a preview

Saves results to DynamoDB

DynamoDB Table: Stores the processing results with metadata.

Project Structure
TextFileProcessor/
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            └── com
                └── example
                    └── TextFileProcessor.java


TextFileProcessor.java — Lambda handler implementation

pom.xml — Maven configuration and dependencies

AWS Resources
1. S3 Bucket

Name: file-processing-bucket-<your-name>

Trigger: ObjectCreated event for .txt files

2. DynamoDB Table

Table Name: FileProcessingResults

Partition Key: fileName (String)

Attributes: lineCount, wordCount, charCount, preview, processedDate

3. IAM Role for Lambda

Permissions required:

Read from S3

Write to DynamoDB

Basic Lambda execution role (CloudWatch logs)
<img width="1346" height="598" alt="image" src="https://github.com/user-attachments/assets/acb37de5-c07c-4937-aeba-d3e14427ff6c" />

Build Instructions

Ensure Java 17 and Maven are installed.

In the project root, run:

mvn clean package


The deployable JAR is located at:

target/TextFileProcessor-1.0.jar

Deploying to AWS Lambda

Go to AWS Lambda → Create Function → Author from Scratch

Runtime: Java 17

Upload the JAR (TextFileProcessor-1.0.jar)

Handler:

com.example.TextFileProcessor::handleRequest


Assign the IAM role created earlier.

Add S3 bucket trigger for .txt file uploads.
Testing

Upload a .txt file to your S3 bucket (e.g., sample.txt).

Check CloudWatch Logs to ensure Lambda executed without errors.

Verify the DynamoDB table contains a new entry:

{
  "fileName": "sample.txt",
  "lineCount": 5,
  "wordCount": 43,
  "charCount": 271,
  "preview": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nVestibulum ac velit sit amet tortor cursu",
  "processedDate": "2025-11-03T12:00:00Z"
}

Dependencies

Managed via Maven:

com.amazonaws:aws-lambda-java-core:1.2.3

com.amazonaws:aws-lambda-java-events:3.11.4

software.amazon.awssdk:s3

software.amazon.awssdk:dynamodb
