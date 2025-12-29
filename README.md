# Notes API

A RESTful Notes management service built with **Spring Boot**, demonstrating clean API design, layered architecture, validation, pagination, filtering, and database migrations using **JPA + Flyway**.

This project is intentionally designed as a **production-style backend service**, focused on backend correctness, clarity, and real-world practices.
.

---

## Features

- CRUD operations for notes  
- Partial updates using `PATCH`  
- Input validation with meaningful error responses  
- Pagination and sorting  
- Dynamic multi-field filtering (title + content)  
- Global exception handling  
- Database migrations with Flyway  
- Multiple environment support (H2 for dev, PostgreSQL for prod)  
- Clean Git history with focused PRs  

---

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **Flyway**
- **H2** (development)
- **PostgreSQL** (production)
- **Maven**

---

## Project Structure

```

src/main/java/com/example/notes
├── controller        # REST controllers
├── service           # Business logic
├── repository        # JPA repositories
├── model             # JPA entities
├── dto               # Request DTOs
├── exception         # Custom exceptions + handlers
└── NotesApplication

```

---

## API Endpoints

### Create a note
```

POST /notes

````

**Request body**
```json
{
  "title": "My note",
  "content": "Some content"
}
````

---

### Get all notes (pagination + filtering)

```
GET /notes?page=0&size=10&sort=id,desc&title=meeting&content=todo
```

**Query parameters**

* `page` – page number
* `size` – page size
* `sort` – sorting field and direction
* `title` – optional title filter (case-insensitive)
* `content` – optional content filter (case-insensitive)

---

### Get note by ID

```
GET /notes/{id}
```

---

### Update a note (partial update)

```
PATCH /notes/{id}
```

**Request body**

```json
{
  "title": "Updated title"
}
```

---

### Delete a note

```
DELETE /notes/{id}
```

---

## Validation & Error Handling

* Request validation via `jakarta.validation`
* Centralized exception handling
* Clear, structured error responses

**Example validation error**

```json
{
  "status": 400,
  "errors": {
    "title": "Title must not be blank"
  }
}
```

---

## Database Setup

### Development (H2)

* Runs automatically
* No setup required
* H2 console available at `/h2-console`

---

### Production (PostgreSQL)

Example `application-postgres.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/notes_db
spring.datasource.username=notes_user
spring.datasource.password=notes_pass

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
```

---

## Flyway Migrations

* Schema changes are managed via Flyway
* Migration files are located in:

```
src/main/resources/db/migration
```

* Flyway runs automatically on application startup

---

## Running the Application

### Prerequisites

* Java 17
* Maven
* (Optional) Docker for PostgreSQL

### Run locally

```bash
mvn spring-boot:run
```

### Run with PostgreSQL profile

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

---

## Git & Workflow Practices

* Feature branches for major changes
* Small, focused commits
* Clear PR descriptions
* No secrets committed
* `.env` and override configs are gitignored

---

## Future Improvements

* Authentication & authorization
* API documentation (OpenAPI / Swagger)
* Integration tests with Testcontainers
* CI pipeline
* Soft deletes & auditing
* Rate limiting

---

## Author

Built by **Archana Dubey**
