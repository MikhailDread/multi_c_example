FROM maven:3.9-eclipse-temurin-11-focal as builder

WORKDIR /app

COPY . .

RUN mvn package && ls -lah



FROM eclipse-temurin:11.0.22_7-jre-alpine

WORKDIR app

COPY --from=builder /app/target/*.jar application.jar

EXPOSE 8080
CMD ["java","-jar","/app/application.jar"]