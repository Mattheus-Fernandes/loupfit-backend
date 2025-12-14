FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/consumables-service-0.0.1-SNAPSHOT.jar /app/consumables-service.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/consumables-service.jar"]

