FROM 	openjdk:8-jdk-alpine
LABEL maintainer="M.Gssair@htwg-konstanz.de"
# Change to Port to 8002 here and in application.properties!
EXPOSE 	8080
VOLUME /tmp 
# Make sure the path to jar file is correct
# TODO: Move Dockerfile to Deployment Dir ?
ARG JAR_FILE=./plant-browser-release/plant-browser-service.jar
ADD ${JAR_FILE} plant-browser-service.jar 
ENTRYPOINT 	["java", "-jar", "plant-browser-service.jar"]
