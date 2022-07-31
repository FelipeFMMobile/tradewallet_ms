FROM openjdk:11
ARG JAR_FILE=stock/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} stock-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/stock-0.0.1-SNAPSHOT.jar"]