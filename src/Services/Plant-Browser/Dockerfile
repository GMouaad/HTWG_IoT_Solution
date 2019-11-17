FROM 	openjdk:8-jdk-alpine
EXPOSE 	8002
VOLUME /tmp
#ADD 	/plant-browser-release/plant-browser-service.jar	 plant-browser-service.jar  
ARG JAR_FILE
COPY ${JAR_FILE} /plant-browser-release/plant-browser-service.jar
ENTRYPOINT 	["java", "-jar", "plant-browser-service.jar"]
