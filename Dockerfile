FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/desafio-dbserver-pauta.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "desafio-dbserver-pauta.jar"]