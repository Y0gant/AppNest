# AppNest

**AppNest** is a full-stack Java web application built using Servlets, JSP, and the MVC architecture. The project demonstrates authentication, session management, and dynamic content rendering. It integrates a PostgreSQL database for persistent user management and uses JDBC for database connectivity.

The application includes mini-apps like Weather App (using the OpenWeatherMap API), Tic-Tac-Toe, and Stone-Paper-Scissors. All apps are accessible through a secure user dashboard. The frontend is styled with modern responsive CSS and includes JavaScript-based client-side validation. The project uses Maven for dependency management, Logback for logging, and Dotenv for managing sensitive environment variables. It's fully containerized with Docker, allowing easy deployment and local testing.

---

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Environment Variables](#environment-variables)
- [Database Setup](#database-setup)
- [Run Locally](#run-locally)
- [Troubleshooting](#troubleshooting)
- [Support](#support)
- [License](#license)

---

## Features

- **User Authentication**: Secure login/logout with session management
- **Weather App**: Real-time weather data using OpenWeatherMap API
- **Tic-Tac-Toe**: Interactive game with JavaScript
- **Stone-Paper-Scissors**: Classic game implementation
- **Responsive Design**: Modern CSS with mobile-friendly interface
- **Secure Session Management**: User sessions with proper authentication
- **Containerized Deployment**: Docker support for easy deployment

---

## Prerequisites

1. **Docker**: Install Docker from [docker.com](https://www.docker.com/get-started)
2. **PostgreSQL**: Install PostgreSQL (locally or in a Docker container)
3. **JDK 21**: The Docker image uses Tomcat 10 with JDK 21
4. **Maven**: Install Maven from [maven.apache.org](https://maven.apache.org/)

---

## Environment Variables

To run this project, you need to create a `.env` file with the following environment variables at:

`src/main/resources/.env`

```env
DB_URL=your_database_url
DB_USER=your_database_user
DB_PASSWORD=your_database_password
WEATHER_API=your_weather_api_key
```

### Getting Weather API Key
1. Sign up at [OpenWeatherMap](https://openweathermap.org/api)
2. Generate your free API key
3. Add it to your `.env` file as `WEATHER_API=your_api_key_here`

---

## Database Setup

### Create Database
```sql
CREATE DATABASE appnest;
```

Connect to this database, then:

### Enable UUID Extension
This table requires the uuid-ossp extension for generating UUIDs. Ensure that the uuid-ossp extension is enabled in your PostgreSQL instance.

You can enable the extension by running the following command once on PostgreSQL (psql) shell:

```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

### Create the Users Table
Run the following SQL script to create the users table in your PostgreSQL database:

```sql
CREATE TABLE users
(
    user_id  uuid DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
    username varchar(50)                     NOT NULL UNIQUE,
    password varchar(100)                    NOT NULL,
    email    varchar(100)
);
```

---

## Run Locally

### 1. Build the Project
To build the project locally, ensure you have Maven installed.

Verify Maven installation:
```bash
mvn -version
```

Build the project:
```bash
mvn clean install
```

This will package the project into a `.war` file in the target directory.

### 2. Build Docker Image
Once the project is built, you can build a Docker image for the application.

Make sure you are in the project's root directory (where the Dockerfile is located).

Build the Docker image:
```bash
docker build -t appnest:latest .
```

### 3. Run Docker Container
Run the Docker container to start the application:

**Basic run:**
```bash
docker run -d -p 8080:8080 --name appnest appnest:latest
```

**With environment file mounting:**
```bash
docker run -d -p 8080:8080 \
  -v ${PWD}/.env:/usr/local/tomcat/.env \
  --name appnest \
  appnest:latest
```

**Note:** Ensure that the `.env` file exists in your current working directory (`${PWD}`) and contains the required environment variables.

The `-v ${PWD}/.env:/usr/local/tomcat/.env` flag mounts the `.env` file from your current working directory to the `/usr/local/tomcat/.env` path in the container, allowing your container to use environment variables defined in the `.env` file.

This will map port 8080 on your local machine to port 8080 in the Docker container.

### 4. Access the Application
Once the container is running, you can access the app in your browser:

```
http://localhost:8080
```

### 5. Stop the Docker Container
To stop the Docker container, run:

```bash
docker stop appnest
```

To remove the container:
```bash
docker rm appnest
```

---

## Troubleshooting

### Common Issues

**Port Already in Use:**
- Ensure port 8080 is not already in use by another application
- Use `docker ps` to check running containers
- Try using a different port: `-p 8081:8080`

**Database Connection Issues:**
- Verify PostgreSQL is running and accessible
- Check that all environment variables in `.env` are set correctly
- Ensure database URL format is correct (e.g., `jdbc:postgresql://localhost:5432/appnest`)

**Environment Variables Not Loading:**
- Verify the `.env` file exists in the correct location
- Check file permissions and syntax
- Ensure Docker volume mounting is correct

**Build Issues:**
- Verify Maven is properly installed and configured
- Check Java version compatibility (JDK 21 required)
- Run `mvn clean` before `mvn install`

**Docker Issues:**
- Ensure Docker daemon is running
- Check Docker has sufficient resources allocated
- Verify Docker image was built successfully

---

## Support

For support, email yogantfaye@gmail.com or connect on LinkedIn - [www.linkedin.com/in/yogant-faye](https://www.linkedin.com/in/yogant-faye)

---

## License

[MIT](https://choosealicense.com/licenses/mit/)