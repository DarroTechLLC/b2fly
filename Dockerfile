FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN chmod +x ./gradlew  # Add this line to give execute permission
RUN ./gradlew bootJar --no-daemon

# Final Stage
FROM openjdk:17-ea-3-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/b2fly-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
