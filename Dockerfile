# Fetching latest version of Java
FROM openjdk:17

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY web/target/web-1.0-SNAPSHOT.jar /app

# Exposing port 8080
EXPOSE 8081

# Starting the application
CMD ["java", "-jar", "web-1.0-SNAPSHOT.jar"]