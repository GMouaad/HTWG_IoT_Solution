FROM 	openjdk:8-jdk-alpine
LABEL maintainer="M.Gssair@htwg-konstanz.de"

#VOLUME /MQTT-Connector/
RUN mkdir /home/MQTT-Connector
WORKDIR /home/MQTT-Connector

# Make sure the path to jar file is correct
ARG JAR_FILE=./MQTT-Connector-1.1.2.jar
ARG CONFIG_FILE=./config.yml

ADD ${JAR_FILE} MQTT-Connector.jar
ADD ${CONFIG_FILE} config.yml

ENTRYPOINT 	["java", "-jar", "MQTT-Connector.jar", "config.yml"]
