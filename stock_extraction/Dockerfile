FROM openjdk:11
ARG JAR_FILE=stock_extraction/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} stock_extraction-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/stock_extraction-0.0.1-SNAPSHOT.jar"]