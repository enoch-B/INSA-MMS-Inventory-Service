# Use the same Java version as your local environment
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR (make sure the name matches your pom.xml)
COPY target/Inventory-Information-0.0.1-SNAPSHOT.jar Inventory-Information-0.0.1-SNAPSHOT.jar

# Expose the correct port (must match application.yml/server.port)
EXPOSE 8081

# Run with debug flags and dependency checks
ENTRYPOINT ["java", "-Djdk.tls.client.protocols=TLSv1.2", "-jar" ,"Inventory-Information-0.0.1-SNAPSHOT.jar"]