FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/jwt-20242-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} jwt-20242-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_fromzero.jar"]