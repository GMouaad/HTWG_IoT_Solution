package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface IMessageProcessor {
    void processMessage(String message) throws Exception;
    void processMessage(String app_id, String dev_id, String message) throws Exception;
}
