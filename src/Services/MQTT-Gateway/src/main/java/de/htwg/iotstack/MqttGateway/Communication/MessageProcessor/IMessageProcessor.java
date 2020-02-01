package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface IMessageProcessor {
    void processMessage(MqttMessage message) throws Exception;
}
