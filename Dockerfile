FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/jira.jar /app/
COPY resources /app/resources
COPY config /app/config
COPY .env /app/.env

EXPOSE 8080

CMD ["java", "-jar", "jira.jar"]