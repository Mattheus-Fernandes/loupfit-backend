FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/user-service.jar"]