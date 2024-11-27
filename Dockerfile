# Use Ubuntu as a base image for the build stage
FROM ubuntu:latest AS build

# Update package lists and install Java 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Copy the project files into the build stage
COPY . .

# Ensure the Gradle wrapper script is executable
RUN chmod +x ./gradlew

# Build the project with Gradle to create the bootable JAR file
RUN ./gradlew bootJar --no-daemon

# Final stage: Use a slim image with Java 17 for the application
FROM openjdk:17-jdk-slim AS runtime

# Expose port 8080 for the application
EXPOSE 8080

# Copy the bootable JAR file from the build stage
COPY --from=build /build/libs/b2fly-0.0.1-SNAPSHOT.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
