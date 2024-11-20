FROM openjdk:17-jdk-slim

WORKDIR /ap

COPY target/backend-0.0.1-SNAPSHOT.jar /ap/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/ap/app.jar"]