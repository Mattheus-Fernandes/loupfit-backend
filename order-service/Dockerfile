FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar /app/order-service.jar

EXPOSE 8085

CMD ["java", "-jar", "/app/order-service.jar"]