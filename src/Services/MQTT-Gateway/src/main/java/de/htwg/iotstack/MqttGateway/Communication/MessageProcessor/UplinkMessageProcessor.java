package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.IMessageProcessor;
import de.htwg.iotstack.MqttGateway.Management.main;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UplinkMessageProcessor implements IMessageProcessor {
    Logger logger = null;

    public UplinkMessageProcessor() {
        this.logger = main.getLogger();
    }

    @Override
    public void processMessage(MqttMessage message) throws Exception {
        //TODO: SDo smth with Uplink Payload
        logger.log(Level.INFO, "Uplink Message arrived" + message.toString());
    }
}
