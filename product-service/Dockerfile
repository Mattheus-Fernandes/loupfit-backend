FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/product-service-0.0.1-SNAPSHOT.jar /app/product-service.jar

EXPOSE 8084

CMD ["java", "-jar", "/app/product-service.jar"]