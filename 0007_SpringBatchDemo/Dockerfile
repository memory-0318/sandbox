FROM openjdk:8-jdk-alpine
COPY ./target/*.jar /app/demo.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "demo.jar"]