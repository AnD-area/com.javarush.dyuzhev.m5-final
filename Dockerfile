FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /target/jira.jar /app/
COPY /resources /app/resources
COPY /config /app/config
COPY .env /app/.env

EXPOSE 8080

#RUN cat /app/.env
#
## Копирование и использование скрипта запуска
#COPY entrypoint.sh /app/entrypoint.sh
#ENTRYPOINT ["/app/entrypoint.sh"]

CMD ["java", "-jar", "jira.jar"]