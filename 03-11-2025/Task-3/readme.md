# 🪶 Text File Processor Lambda

A **Java 17 AWS Lambda function** that automatically processes `.txt` files uploaded to an **S3 bucket**.  
The Lambda function counts **lines**, **words**, and **characters**, extracts a **100-character preview**, and stores the results in a **DynamoDB** table.

---

## 📘 Project Overview

### Workflow Summary
1. **S3 Bucket** – Receives `.txt` file uploads and triggers the Lambda function.  
2. **Lambda Function** – Processes each file by:
   - Counting lines, words, and characters  
   - Extracting the first 100 characters as a preview  
   - Storing results in DynamoDB  
3. **DynamoDB Table** – Stores the file processing results and metadata.

---

## 🗂️ Project Structure

TextFileProcessor/
├── pom.xml
├── README.md
└── src
└── main
└── java
└── com
└── example
└── TextFileProcessor.java

- **TextFileProcessor.java** — Main Lambda handler implementation  
- **pom.xml** — Maven configuration and dependencies  

---

## ☁️ AWS Resources

### 1. S3 Bucket
- **Name:** `file-processing-bucket-<your-name>`  
- **Trigger:** `ObjectCreated` event for `.txt` files  

### 2. DynamoDB Table
- **Table Name:** `FileProcessingResults`  
- **Partition Key:** `fileName` (String)  
- **Attributes:** `lineCount`, `wordCount`, `charCount`, `preview`, `processedDate`  

### 3. IAM Role for Lambda
Permissions required:
- Read from S3  
- Write to DynamoDB  
- Basic Lambda execution permissions (CloudWatch logs)

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/acb37de5-c07c-4937-aeba-d3e14427ff6c" alt="AWS Architecture Overview">
</p>

---

## 🛠️ Build Instructions

Ensure you have **Java 17** and **Maven** installed.

In the project root, run:
```bash
mvn clean package

target/TextFileProcessor-1.0.jar

## 🚀 Deploying to AWS Lambda

Go to AWS Lambda Console → Create Function → Author from Scratch

Runtime: Java 17

Upload the built JAR (TextFileProcessor-1.0.jar)

Handler:

com.example.TextFileProcessor::handleRequest


Assign the IAM role created earlier

Add S3 bucket trigger for .txt file uploads
## 🧪 Testing

Upload a .txt file (e.g., sample.txt) to your S3 bucket.

Check CloudWatch Logs for successful execution.

Verify the DynamoDB table contains the new record:
