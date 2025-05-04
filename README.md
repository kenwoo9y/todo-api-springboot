# todo-api-springboot

This is a ToDo Web API implemented with Spring Boot, designed for simplicity and extensibility.

## Tech Stack

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=mybatis&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

### Programming Languages
- [Java](https://www.java.com/) v21 - Primary development language

### Backend
- [Spring Boot](https://spring.io/projects/spring-boot) v3.2.3 - Java-based framework for building web applications
- [MyBatis](https://mybatis.org/mybatis-3/) v3.0.4 - SQL mapper framework
- [Flyway](https://flywaydb.org/) v9.16.3 - Database migration tool

### Database
- [MySQL](https://www.mysql.com/) v8.0 - Primary relational database
- [PostgreSQL](https://www.postgresql.org/) v16 - Alternative relational database

### Development Environment
- [Gradle](https://gradle.org/) - Build automation tool
- [Docker](https://www.docker.com/) with Compose v3.9 - Containerization platform for building and managing applications

### Testing & Quality Assurance
- [JUnit 5](https://junit.org/junit5/) - Java testing framework
- [JaCoCo](https://www.eclemma.org/jacoco/) v0.8.11 - Code coverage tool
- [Spotless](https://github.com/diffplug/spotless) v6.25.0 - Code formatting tool
- [Checkstyle](https://checkstyle.sourceforge.io/) v10.20.1 - Code style checker

### CI/CD
- GitHub Actions - Continuous Integration and Deployment

## Setup
### Initial Setup
1. Clone this repository:
    ```
    $ git clone https://github.com/kenwoo9y/todo-api-springboot.git
    $ cd todo-api-springboot
    ```

2. Build the required Docker images:
    ```
    $ make build-local
    ```

3. Start the containers and run database migrations:
    ```
    $ make up
    ```
    This command will:
    - Start all required containers
    - Run database migrations automatically
    - Start the Spring Boot application

## Usage
### Container Management
- Check container status:
    ```
    $ make ps
    ```
- View container logs:
    ```
    $ make logs
    ```
- Stop containers:
    ```
    $ make down
    ```

## Development
### Running Tests
- Run tests:
    ```
    $ make test
    ```
### Code Quality Checks
- Lint check:
    ```
    $ make lint-check
    ```
- Check code formatting:
    ```
    $ make format-check
    ```
- Apply code formatting:
    ```
    $ make format-fix
    ```

## Database
### Switching Database
1. Edit `.env` file:

For MySQL:
```
DB_TYPE=mysql
DB_HOST=mysql-db
DB_PORT=3306
DB_NAME=todo
DB_USER=<your_username>
DB_PASSWORD=<your_password>
```

For PostgreSQL:
```
DB_TYPE=psql
DB_HOST=postgresql-db
DB_PORT=5432
DB_NAME=todo
DB_USER=<your_username>
DB_PASSWORD=<your_password>
```

2. Edit `api/src/main/resources/db/migration/V1__create_users_and_tasks_tables.sql` file

3. Rebuild and restart the application:
```
$ make build-local
$ make up
```

### Database Access
- Access MySQL database:
    ```
    $ make mysql
    ```
- Access PostgreSQL database:
    ```
    $ make psql
    ```