# Start from a base image that has Java 17
FROM eclipse-temurin:17-jre-focal

# Set an argument for the JAR file name
ARG JAR_FILE=target/producer-service-0.0.1-SNAPSHOT.jar

# Copy the built JAR file from our local 'target' directory
# into the Docker image
COPY ${JAR_FILE} app.jar

# The command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]