FROM maven:3.8-openjdk-17 AS BUILD
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=BUILD /app/target/*.jar /app/product-service.jar
EXPOSE 8084
CMD ["java", "-jar", "/app/product-service.jar"]