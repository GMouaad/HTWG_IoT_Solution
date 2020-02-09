package de.htwg.iotstack.MqttGateway.Communication.MessageProcessor;

import de.htwg.iotstack.MqttGateway.Management.main;
import de.htwg.iotstack.MqttGateway.Persistence.DBController;
import de.htwg.iotstack.MqttGateway.Persistence.TTNMessage;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JoinMessageProcessor implements IMessageProcessor{
    Logger logger = null;
    DBController dbController = null;

    public JoinMessageProcessor() {
        this.logger = main.getLogger();
        this.dbController = DBController.getInstance();
    }

    @Override
    public void processMessage(String  message) throws Exception {
        logger.log(Level.INFO, "Join Message arrived" + message);
    }

    @Override
    public void processMessage(String app_id, String dev_id, String message) throws Exception {
        logger.log(Level.INFO, "Join Message arrived" + message);
        TTNMessage joinMessage = new TTNMessage(app_id, dev_id, "join", message);
        dbController.persist(joinMessage);
    }
}
