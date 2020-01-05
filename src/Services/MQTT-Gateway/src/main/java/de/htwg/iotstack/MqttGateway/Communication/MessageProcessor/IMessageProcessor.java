package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface IMessageProcessor {
    void processMessage(String topic, MqttMessage message) throws Exception;
}
