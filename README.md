# credit_card_proccesin
# Credit Card Processing Application

This project consists of a Spring Boot backend and a React frontend that together simulate a credit card processing flow including user authentication, a shopping cart, and payment processing.

## Prerequisites

- **Backend:** Java (JDK 11+), Maven (or Maven Wrapper)
- **Frontend:** Node.js, npm

## How to Run

### 1. Run the Backend

- **Navigate to the backend project root** (if separate, e.g., `cd backend`):
  - **Using Maven Wrapper:**
    ```bash
    ./mvnw spring-boot:run -Dspring-boot.run.arguments="--mercado_pago_sample_public_key=YOUR_PUBLIC_KEY --mercado_pago_sample_access_token=YOUR_ACCESS_TOKEN"
    ```
  - **Using Global Maven:**
    ```bash
    mvn spring-boot:run -Dspring-boot.run.arguments="--mercado_pago_sample_public_key=YOUR_PUBLIC_KEY --mercado_pago_sample_access_token=YOUR_ACCESS_TOKEN"
    ```
  *(Replace `YOUR_PUBLIC_KEY` and `YOUR_ACCESS_TOKEN` with your actual credentials.)*

### 2. Run the Frontend

- **Navigate to the frontend project folder:**
  ```bash
  cd credit-card-processing-frontend
Install dependencies:

npm install

Start the React development server:

npm start