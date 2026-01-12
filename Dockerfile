# Use openjdk:21 as base image for build
FROM openjdk:21-jdk-slim AS builder

# Set working directory to /app/api
WORKDIR /app/api

# Copy Gradle Wrapper and project files
COPY ./api/gradlew ./gradlew
COPY ./api/gradle ./gradle
COPY ./api/build.gradle ./build.gradle
COPY ./api/settings.gradle ./settings.gradle
COPY ./api/src ./src
COPY ./api/config ./config

# Grant execute permission to Gradle wrapper
RUN chmod +x ./gradlew

# Run Gradle to build the project (skip tests)
RUN ./gradlew bootJar --no-daemon --info --stacktrace

# Verify build artifacts
RUN ls -la build/libs/ || true
RUN find . -name "*.jar" || true

# Use openjdk:21 as base image for runtime
FROM openjdk:21-jdk-slim

# Set working directory to /app/api
WORKDIR /app/api

# Copy build artifacts (JAR file)
COPY --from=builder /app/api/build/libs/todo-0.0.1-SNAPSHOT.jar /app/api/todo-api.jar

# Grant execute permission to JAR file
RUN chmod +x /app/api/todo-api.jar

# Verify file existence
RUN ls -la /app/api/todo-api.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/api/todo-api.jar"]

# Expose port
EXPOSE 8080
