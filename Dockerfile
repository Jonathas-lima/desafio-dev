FROM openjdk:11-jdk
MAINTAINER Jonathas Lima
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar
COPY wait-for-it.sh .
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "mysqlsrv:3306", "-t",  "40", "--", "java", "-jar", "app.jar"]