
FROM openjdk:17-alpine


WORKDIR /app


COPY target/*.jar app.jar

COPY ./localstorage /app/localstorage

ENV token=""
# Port
EXPOSE 7777


CMD ["sh", "-c", "java -Dchat_api_token=$token -jar app.jar"]
