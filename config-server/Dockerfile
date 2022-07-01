FROM openjdk:11
ARG JAR_FILE=config-server/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} config-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/config-server-0.0.1-SNAPSHOT.jar"]