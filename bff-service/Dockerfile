FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/bff-service-0.0.1-SNAPSHOT.jar /app/bff-service.jar

EXPOSE 8001

CMD ["java", "-jar", "/app/bff-service.jar"]