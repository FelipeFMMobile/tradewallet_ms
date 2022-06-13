FROM openjdk:11
ARG JAR_FILE=naming-server/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} naming-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/naming-server-0.0.1-SNAPSHOT.jar"]