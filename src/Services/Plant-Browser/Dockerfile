FROM 	openjdk:8-jdk-alpine
LABEL maintainer="M.Gssair@htwg-konstanz.de"
EXPOSE 	8002
VOLUME /tmp 
# Make sure the path to jar file is correct
# TODO: Move Dockerfile to Deployment Dir ?
ARG JAR_FILE=./plant-browser-release/plant-browser-service.jar
ADD ${JAR_FILE} plant-browser-service.jar 
ENTRYPOINT 	["java", "-jar", "plant-browser-service.jar"]
