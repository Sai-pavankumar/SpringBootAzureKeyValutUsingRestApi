FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} app.jar
STOPSIGNAL SIGTERM
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
