# 📘 Java 21 API with PostgreSQL - Dockerized

This project is a RESTful API built with **Spring Boot (Java 21)** and **PostgreSQL**. It performs basic arithmetic operations and logs API calls. The entire system runs using **Docker** and **Docker Compose**.

---

## Requirements

Make sure you have the following installed:

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/)

---

## How to Run the Project (Step-by-Step)

1. **Clone this repository** (if you haven't already):
   ```
   git clone https://github.com/JimmyVB/calculation-api.git
   cd calculation-api

2. Enter the project directory and build the application:
   ```
   cd calculation-api
   
3. Run the containers with Docker Compose:
   ```
   cd docker
   docker-compose up --build
   
4. Wait until the app starts. Once running, the API will be available at:
    ```
   http://localhost:8080/

## API Documentation (Swagger)
You can explore the API visually using Swagger UI:

    http://localhost:8080/swagger-ui/index.html

## Available Endpoints

| Method | Endpoint        | Description                                                                 |
|--------|------------------|-----------------------------------------------------------------------------|
| GET    | `/api/calculate` | Performs a calculation using two numbers and an internal percentage         |
| GET    | `/api/logs`      | Returns a paginated list of logged API calls (sorted by newest first)       |

### Example Usage

#### `/api/calculate`
- **Query Parameters**:
    - `num1`: First number (e.g., 10)
    - `num2`: Second number (e.g., 20)
- **Response**: Returns the sum of both numbers plus a calculated percentage.

#### `/api/logs`
- **Query Parameters**:
    - `page`: (optional) Page number (default is `0`)
    - `size`: (optional) Number of records per page (default is `10`)
- **Response**: A paginated list of API call logs.

## How to Stop the Project
    docker-compose down


