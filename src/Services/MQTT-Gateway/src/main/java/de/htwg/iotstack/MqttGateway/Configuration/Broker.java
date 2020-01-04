package de.htwg.iotstack.MqttGateway.Configuration;

import java.security.PrivateKey;

public class Broker {
    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "MQTT Broker on " +
                "host=tcp://" + host + ":" + port + "\n";
    }
}
