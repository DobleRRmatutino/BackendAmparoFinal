FROM amazoncorretto:17
MAINTAINER EDO
COPY target/jwt-20242-api-0.0.1-SNAPSHOT.jar d.jar
ENTRYPOINT ["java", "-jar", "/d.jar"]