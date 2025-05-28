# Use a Java 21 runtime base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy JAR file into container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

#COPY wait-for.sh /wait-for.sh
#RUN chmod +x /wait-for.sh
#ENTRYPOINT ["./wait-for.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
