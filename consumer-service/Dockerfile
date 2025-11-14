# Start from a base image that has Java 17
FROM eclipse-temurin:17-jre-focal

# Set an argument for the JAR file name
ARG JAR_FILE=target/consumer-service-0.0.1-SNAPSHOT.jar

# Copy the built JAR file into the image
COPY ${JAR_FILE} app.jar

# The command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]