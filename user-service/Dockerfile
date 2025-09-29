FROM openjdk:17-alpine

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

EXPOSE 8089

CMD ["java", "-jar", "/app/user-service.jar"]