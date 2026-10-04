# FoodHub - Java + MySQL Docker Practice

A simple food application built with **Java 21 + Spring Boot + Spring Data JPA + MySQL 8.4**.

## Features
- Food menu stored in MySQL
- REST APIs for CRUD operations
- Categories and available-food API
- Simple browser UI
- Sample food data loaded automatically
- Dockerfile for the Java application
- Separate MySQL container with persistent volume
- Docker Compose included for easy testing

## 1. Run locally without Docker
Requirements: Java 21, Maven, MySQL.

Create database/user matching `application.properties`, then:

```bash
mvn clean package
java -jar target/food-app-1.0.0.jar
```

Open: http://localhost:8080

## 2. Build the application image
```bash
docker build -t food-app:1.0 .
```

## 3. Run the DB container
```bash
docker volume create food_db_data

docker run -d --name food-db \
  -e MYSQL_DATABASE=fooddb \
  -e MYSQL_USER=fooduser \
  -e MYSQL_PASSWORD=foodpass \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -v food_db_data:/var/lib/mysql \
  -p 3306:3306 \
  mysql:8.4
```

## 4. Run the Java app container
The important point is that **localhost inside the app container means the app container itself**. Therefore DB_HOST must be the database container/network name, not localhost.

Create a Docker network:
```bash
docker network create food-network
```

Run DB on the network:
```bash
docker rm -f food-db 2>/dev/null || true

docker run -d --name food-db --network food-network \
  -e MYSQL_DATABASE=fooddb \
  -e MYSQL_USER=fooduser \
  -e MYSQL_PASSWORD=foodpass \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -v food_db_data:/var/lib/mysql \
  mysql:8.4
```

Run app:
```bash
docker run -d --name food-app --network food-network -p 8080:8080 \
  -e DB_URL='jdbc:mysql://food-db:3306/fooddb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC' \
  -e DB_USERNAME=fooduser \
  -e DB_PASSWORD=foodpass \
  food-app:1.0
```

Open:
http://localhost:8080

Check logs:
```bash
docker logs -f food-app
docker logs -f food-db
```

## 5. Easiest method: Docker Compose
```bash
docker compose up -d --build
```

Open:
http://localhost:8080

Stop:
```bash
docker compose down
```

Stop and remove DB data too:
```bash
docker compose down -v
```

## REST APIs

### Get all food
```http
GET /api/foods
```

### Get available food
```http
GET /api/foods/available
```

### Get by ID
```http
GET /api/foods/1
```

### Get by category
```http
GET /api/foods/category/Biryani
```

### Create food
```http
POST /api/foods
Content-Type: application/json

{
  "name": "Chicken Tikka",
  "category": "Starters",
  "price": 229,
  "description": "Grilled chicken tikka",
  "available": true
}
```

### Update food
```http
PUT /api/foods/1
Content-Type: application/json

{
  "name": "Chicken Biryani Special",
  "category": "Biryani",
  "price": 279,
  "description": "Special Hyderabadi chicken biryani",
  "available": true
}
```

### Delete food
```http
DELETE /api/foods/1
```

## Docker architecture

Browser -> `localhost:8080` -> **food-app container** -> Docker network -> **food-db container** -> MySQL volume `/var/lib/mysql`

The app container does NOT need MySQL installed inside it. The database runs independently in the DB container.
