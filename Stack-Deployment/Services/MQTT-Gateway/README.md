# MQTT-Gateway
This Service acts as a Message processor. It subscribes to the topics of the given TTN Application in the config file, logs the messages, and (still to be implemented evtl.) persists the data in the InfluxDB Time-Series Database. 

## Dependencies
 - This Service depends on the MQTT Broker from where the data comes
 - This Service dependes on the Database to where the data should be persisted

## Run on Docker

### Build image 
 ```docker image build -t mqtt-gateway .```

### Run the image
 ```docker run --name my-mqtt-Gateway mqtt-gateway```

### Rebuild if the Application changed (new version)
```docker rmi mqtt-gateway```
```docker build -t mqtt-gateway .```