FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/supplier-service-0.0.1-SNAPSHOT.jar /app/supplier-service.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/supplier-service.jar"]