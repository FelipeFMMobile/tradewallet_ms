FROM openjdk:11
ARG JAR_FILE=api-gateway/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} api-gateway-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/api-gateway-0.0.1-SNAPSHOT.jar"]