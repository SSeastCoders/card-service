FROM openjdk:11-jdk

RUN adduser --system --group spring
USER spring:spring

ARG SERVICE=card-api
ARG JAR_FILE=${SERVICE}/target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "/app.jar"]
