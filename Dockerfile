# OpenJDK 21
FROM eclipse-temurin:21-jdk

# Work directory
WORKDIR /app

# Copy the jar file
COPY target/challenge-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
