FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/test-accenture-0.0.1-SNAPSHOT.jar test-accenture.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test-accenture.app"]