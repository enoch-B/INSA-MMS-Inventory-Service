# Base image
FROM eclipse-temurin:21-jdk-ubi9-minimal

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/Inventory-Information-0.0.1-SNAPSHOT.jar Inventory-Information-0.0.1-SNAPSHOT.jar

# Expose the port the application runs on
EXPOSE 8194

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]