FROM openjdk:11-jdk-slim

ARG JAR_FILE=./build/libs/microservice-users-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} microservice-users.jar

EXPOSE 8282

ENTRYPOINT ["java", "-jar", "/microservice-users.jar"]