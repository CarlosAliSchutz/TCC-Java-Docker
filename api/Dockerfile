FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build app/target/api-0.0.1-SNAPSHOT.jar /usr/local/lib/api-0.0.1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/api-0.0.1.jar"]
