# Use the official openjdk:17-alpine image as the base
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file from the Jenkins build to the container
COPY *.jar app.jar

COPY ./localstorage /app/localstorage
# Use environment variable in the Dockerfile
ENV token=""

# Run the app using the env variable and the app.jar file
CMD ["sh", "-c", "java -Dchat_api_token=$token -jar app.jar"]
