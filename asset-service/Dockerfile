FROM openjdk:17-alpine

WORKDIR /app

COPY target/asset-service-0.0.1-SNAPSHOT.jar /app/asset-service.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/asset-service.jar"]