# Digital Wallet & Ledger Microservice

A lightweight, production-ready RESTful backend microservice built with **Java 17** and **Spring Boot** to simulate a digital wallet transaction engine and immutable financial ledger.

---

## Key Features

* **Account & Ledger Management:** Processes credit and debit wallet transactions with real-time balance records.
* **Audit Trail & Persistence:** Maintains historical transaction logs using **Spring Data JPA** and an in-memory **H2 Database**.
* **RESTful Architecture:** Exposes clean HTTP endpoints for transaction execution and audit history retrieval.
* **Error Handling & Data Validation:** Ensures transactional integrity and structured JSON responses.

---

## Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.2.0 (Spring Web, Spring Data JPA)
* **Database:** H2 (In-Memory Database)
* **Build Tool:** Maven

---

## API Endpoints

### 1. Record a Transaction
* **Endpoint:** `POST /api/v1/wallet/transaction`
* **Request Body:**
{
  "userEmail": "anusha@example.com",
  "type": "CREDIT",
  "amount": 250.00
}

Response:
{
  "id": 1,
  "userEmail": "anusha@example.com",
  "transactionType": "CREDIT",
  "amount": 250.0,
  "timestamp": "2026-09-24T22:50:00"
}


2. Retrieve User Ledger History

  "type": "CREDIT",
  "amount": 250.00
}


2. Retrieve User Ledger History
Endpoint: GET /api/v1/wallet/ledger/{email}
Response: Returns a list of all historical ledger entries associated with the specified email address.

3. Prerequisites
Java 17 or higher
Maven 3.8+
