# 🛒 Real-Time Order Event Processing Pipeline (AWS Serverless)
 
## 📘 Overview
This project implements a **real-time, event-driven data processing pipeline** for an e-commerce platform using **AWS DynamoDB Streams**, **Amazon EventBridge Pipes**, and **AWS Lambda**.
 
The pipeline automatically reacts to order events (INSERT, MODIFY, REMOVE) in DynamoDB and intelligently routes them to the appropriate processing functions based on order status, amount, and type — ensuring **scalability, cost-efficiency, and fault tolerance**.
 
---
 
## 🧩 Architecture Components
 
### 1. **DynamoDB Table: `Orders`**
- **Primary Key:** `orderId` (String)
- **Attributes:**  
  - `customerEmail` (String)  
  - `orderAmount` (Number)  
  - `orderStatus` (String) — e.g., `pending`, `shipped`, `delivered`
  - `orderDate` (String, ISO 8601 format)
 
- **Streams:** Enabled with `NEW_AND_OLD_IMAGES`
  - Captures **INSERT**, **MODIFY**, and **REMOVE** events
 
---
 
### 2. **EventBridge Pipes**
 
#### 🧮 Pipe 1 — Standard Order Processing
**Source:** DynamoDB Stream (Orders table)  
**Target:** Lambda function `OrderProcessorFunction`
 
**Filter Criteria:**
- `orderStatus` is `pending` **OR** `shipped`
- `orderAmount` > `$100`
- `customerEmail` **does not contain** `"test.com"`
 
**Purpose:**  
Processes standard customer orders that meet basic business conditions.
 
#### 💎 Pipe 2 — Premium Order Processing
**Source:** DynamoDB Stream (Orders table)  
**Target:** Lambda function `PremiumOrderProcessorFunction`
 
**Advanced Filter Criteria:**
- Event type = `MODIFY`
- `oldImage.orderStatus` = `pending`
- `newImage.orderStatus` = `shipped`
- `orderAmount` > `$1000`
 
**Purpose:**  
Handles **high-value premium orders** for enhanced customer service workflows.
 
---
 
### 3. **Lambda Functions**
 
#### 🧩 `OrderProcessorFunction`
- Triggered by **Pipe 1**
- Validates event payload
- Logs or updates order metrics
- Integrates with downstream systems (e.g., notifications, analytics)
 
#### 💠 `PremiumOrderProcessorFunction`
- Triggered by **Pipe 2**
- Handles high-value customers with special workflows (e.g., VIP service, shipping priority)
- May notify account managers or trigger CRM updates
 
---
 
### 4. **Error Handling and Reliability**
 
- **Dead-Letter Queues (DLQs):** Configured for both Pipes  
  → Failed events are sent to **Amazon SQS DLQs**
- **Retries:** Automatic retry for transient Lambda invocation failures
- **Partial Batch Failures:** Enabled for safe reprocessing
- **Monitoring:**  
  - CloudWatch Metrics and Logs  
  - Lambda Insights for performance
 
---
 
## ⚙️ Technical Configuration
 
### DynamoDB Stream
```json
{
  "StreamViewType": "NEW_AND_OLD_IMAGES"
}
