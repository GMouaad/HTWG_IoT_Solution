FROM 	openjdk:8-jdk-alpine
LABEL maintainer="M.Gssair@htwg-konstanz.de"

#VOLUME /MQTT-Gateway/
RUN mkdir /home/MQTT-Gateway
WORKDIR /home/MQTT-Gateway

# Make sure the path to jar file is correct
ARG JAR_FILE=./MQTT-Gateway-1.0.1.jar
ARG CONFIG_FILE=./config.yml

ADD ${JAR_FILE} MQTT-Gateway.jar 
ADD ${CONFIG_FILE} config.yml

ENTRYPOINT 	["java", "-jar", "MQTT-Gateway.jar", "config.yml"]
