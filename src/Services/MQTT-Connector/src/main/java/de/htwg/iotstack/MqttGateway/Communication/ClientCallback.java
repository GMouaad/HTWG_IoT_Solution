package de.htwg.iotstack.MqttGateway.Communication;

import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.IMessageProcessor;
import de.htwg.iotstack.MqttGateway.Management.main;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class implements the MqttCallback Interface.
 * Objects of this Class will be set to a MQTT Client to process the incoming Messages.
 * Ref to : https://stackoverflow.com/questions/36477602/best-practices-in-handling-mqtt-messages-with-paho-java-library
 * for best practices handling Mqtt Massages
 */
public class ClientCallback implements MqttCallback {

    Logger logger = null;
    Map<String, IMessageProcessor> dispatchMap = null;
    private String app_id;
    private String dev_id;
    private String msgType;

    private String regex;// = "v3/(?<app>.*?)/devices/(?<device>.*?)/(?:(?<type>[\\w&&[^/]]*)/?(?<downType>.*?)?)";
    private Pattern pattern;

    public ClientCallback(Map<String, IMessageProcessor> dispatchMap) {
        this.logger = main.getLogger();
        this.dispatchMap = dispatchMap;
        this.regex = main.getConfiguration().getRegex();
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.log(Level.SEVERE,"Lost Connection to the server !..");
        logger.log(Level.SEVERE, "Throwable: " + throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        logger.log(Level.INFO, "mqttMessage received on :" + topic);

        /* Check if topic is <pattern> */
        if (matchTopic(topic,regex)){
            logger.log(Level.INFO, "Dispatching to " + this.msgType + "-MessageProcessor");
            // dispatchMap.get(this.msgType).processMessage(mqttMessage.toString());
            dispatchMap.get(this.msgType).processMessage(app_id, dev_id, mqttMessage.toString());
        } else {
            logger.log(Level.INFO, "Msg didn't match.\n " + mqttMessage.toString());
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public boolean matchTopic(String topic, String regex){
        Matcher matcher = pattern.matcher(topic);

        while(matcher.find()){
            this.app_id = matcher.group("app");
            this.dev_id = matcher.group("device");
            this.msgType = matcher.group("type");

            //System.out.println("count: " + matcher.groupCount());
            logger.log(Level.INFO,"App-id: " + this.app_id
                    + " Device-id: " + this.dev_id
                    + " msgType: " + this.msgType
                    + " /msgType2 : "+matcher.group("downType"));
        }

        return matcher.matches();
    }
}
