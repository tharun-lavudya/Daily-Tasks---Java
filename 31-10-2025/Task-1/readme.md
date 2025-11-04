
# 🚀 Serverless Web Application using AWS Lambda, API Gateway, DynamoDB, and EC2

## 📝 Overview
This project demonstrates a **serverless web application** built using AWS services.  
The application allows users to submit and view form data through a simple HTML interface hosted on an **EC2 instance**.  
Data is processed by **AWS Lambda functions**, routed via **API Gateway**, and stored in **DynamoDB**.

---

## 🏗️ Architecture

**AWS Components Used:**
- **EC2 Instance** → Hosts the static HTML form (frontend)
- **API Gateway** → REST API to connect frontend and Lambda
- **Lambda Functions** → Handles business logic (submit/query)
- **DynamoDB** → Stores form submissions
- **IAM Roles** → Manages access permissions securely

### 🔄 Data Flow
1. User submits the form from the EC2-hosted HTML page.  
2. The form triggers a **POST** request to the **API Gateway** endpoint.  
3. The **Submission Lambda** validates the input and saves data in **DynamoDB**.  
4. A **GET** request to the API Gateway invokes the **Query Lambda** to fetch stored records.  
5. Results are returned and displayed on the frontend.

---

## 🗂️ DynamoDB Table

**Table Name:** `UserSubmissions`

| Attribute       | Type   | Description                    |
|-----------------|--------|--------------------------------|
| `submissionId`  | String | Primary (Partition) Key        |
| `name`          | String | User’s name                    |
| `email`         | String | User’s email address           |
| `message`       | String | User’s message                 |
| `submissionDate`| String | ISO date/time of submission    |
| `status`        | String | Submission status (default: received) |

---

## ⚙️ Lambda Functions

### 1. 📨 Submission Lambda (`SubmissionFunction`)
- **Trigger:** `POST /submit` via API Gateway  
- **Purpose:** Handle form submissions  
- **Actions:**
  - Validate input data (`name`, `email`, `message`)
  - Generate a unique `submissionId`
  - Store the record in DynamoDB
  - Return a success or error JSON response

**Example Response:**
```json
{
  "message": "Submission saved successfully!",
  "submissionId": "abc123"
}

##🔍 Query Lambda (QueryFunction)

Trigger: GET /submissions via API Gateway

Purpose: Retrieve submissions

Query Parameters:

email → Optional, filters submissions by email

Actions:

If email is provided, fetch filtered items

Otherwise, return all records from DynamoDB

## API Gateway Configuration
Method	  Resource	    Lambda              Integration   	Description
POST	    /submit	      SubmissionFunction	Save            new user submissions
GET      	/submissions  QueryFunction	      Fetch           submissions data

Enable CORS for the EC2 domain.

Deploy API to a stage (e.g., prod).

##EC2 Instance Setup

Instance Type: t2.micro
AMI: Amazon Linux 2

Steps:

1. Launch EC2 and SSH into it.

2. Install Apache:
sudo yum update -y
sudo yum install -y httpd
sudo systemctl enable httpd
sudo systemctl start httpd

3. Clone this repository or upload your HTML files to /var/www/html/.

## Testing

Open the EC2 public DNS or IP in your browser.

Submit the form.

Verify data is stored in DynamoDB.

Test GET /submissions via browser or Postman.
