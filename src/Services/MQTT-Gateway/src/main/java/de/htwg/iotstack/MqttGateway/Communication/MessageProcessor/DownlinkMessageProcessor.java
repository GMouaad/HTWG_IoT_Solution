package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import de.htwg.iotstack.MqttGateway.Management.main;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DownlinkMessageProcessor implements IMessageProcessor {
    Logger logger = null;

    public DownlinkMessageProcessor() {
        this.logger = main.getLogger();
    }

    @Override
    public void processMessage(MqttMessage message) throws Exception {
        //TODO: SDo smth with Down Payload
        logger.log(Level.INFO, "Down Message arrived" + message.toString());
    }
}
