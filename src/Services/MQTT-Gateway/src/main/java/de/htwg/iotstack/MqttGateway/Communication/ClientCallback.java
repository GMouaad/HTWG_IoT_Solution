package de.htwg.iotstack.MqttGateway.Communication;

import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.DownlinkMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.IMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.JoinMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.UplinkMessageProcessor;
import de.htwg.iotstack.MqttGateway.Management.main;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class implements the MqttCallback Interface.
 * Objects of this Class will be set to a MQTT Client to process the incoming Messages.
 * Ref to : https://stackoverflow.com/questions/36477602/best-practices-in-handling-mqtt-messages-with-paho-java-library
 * for best practices handling Mqtt Massages
 */
public class ClientCallback implements MqttCallback {

    Logger logger = null;
    Map<String, IMessageProcessor> dispatchMap = null;

    public ClientCallback(Map<String, IMessageProcessor> dispatchMap) {
        this.logger = main.getLogger();
        this.dispatchMap = dispatchMap;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.log(Level.SEVERE,"Lost Connection to the server !..");
        logger.log(Level.SEVERE, throwable.getMessage());
        logger.log(Level.SEVERE, "Cause: " + throwable.getCause());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        logger.log(Level.INFO, "mqttMessage received on :" + topic + " :" + mqttMessage.toString());

        /* Check if topic is <pattern> */
        dispatchMap.get(topic).processMessage(topic, mqttMessage);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
