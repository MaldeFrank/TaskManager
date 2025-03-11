# Stage 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory for the build
WORKDIR /build

# Copy the entire project (including pom.xml and source code)
COPY . .

# Build the project and package it into a JAR file
RUN mvn package -DskipTests

# Stage 2: Run the application with a lightweight runtime
FROM eclipse-temurin:21-jre

# Set the working directory for the runtime
WORKDIR /app

# Copy the JAR file from the builder stage to the runtime stage
COPY --from=builder /build/target/Clean-app-backend-*.jar app.jar

# Run the JAR file
CMD ["java", "-jar", "app.jar"]