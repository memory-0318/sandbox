FROM openjdk:8-jdk-alpine
COPY ./target/*.jar /app/demo.jar
WORKDIR /Documents/mydocker
ENTRYPOINT ["java", "-jar", "demo.jar"]