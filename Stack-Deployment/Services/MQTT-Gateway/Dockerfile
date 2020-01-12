FROM 	openjdk:8-jdk-alpine
LABEL maintainer="M.Gssair@htwg-konstanz.de"

VOLUME ./config.yml:/etc/telegraf/config.yml
# :ro ??

#WORKDIR $HOME/IoTstack/Services

# Make sure the path to jar file is correct
ARG JAR_FILE=./MQTT-Gateway-1.0.1.jar
ADD ${JAR_FILE} MQTT-Gateway.jar 
ENTRYPOINT 	["java", "-jar", "MQTT-Gateway.jar"]
