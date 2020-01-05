package de.htwg.iotstack.MqttGateway.Communication;

import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.DownlinkMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.IMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.JoinMessageProcessor;
import de.htwg.iotstack.MqttGateway.Communication.MessageProcessor.UplinkMessageProcessor;
import de.htwg.iotstack.MqttGateway.Management.main;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class acts as the Mqtt Client Thread, implementing the Runnable Interface.
 * The Singelton aproach in not used, since multiple connections with different Authentications are needed.
 *
 */
public class ClientThread implements Runnable{
    private static final String TAG = "ClientThread " + Thread.currentThread().getId();
    Logger logger = null;

    private String host = "iot.eclipse.org";
    private String port = "1883";
    private String broker = "tcp://" + host + ":" + port;
    private String clientId = "a1"; //TODO: use Thread.currentThread().getId() ??
    private String ttnAppID = null;
    private String ttnAppKey = null;
    private String[] topicFilter = null;
    Map<String, IMessageProcessor> dispatchMap = null;
    private MemoryPersistence persistence = null;
    private MqttConnectOptions connectionOpts = null;
    private boolean connectionStatus;
    private MqttClient mqttClient = null;

    public ClientThread(){

    }

    public ClientThread(String host, String port, String clientId, String ttnAppID, String ttnAppKey, String[] topicFilter) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
        this.ttnAppID = ttnAppID;
        this.ttnAppKey = ttnAppKey;
        this.topicFilter = topicFilter;

        this.logger = main.getLogger();
    }

    @Override
    public void run() {
        init();
        connect();
        subscribeAll(2);
    }

    public void init(){
        try {

            persistence = new MemoryPersistence();
            connectionOpts = new MqttConnectOptions();
            connectionOpts.setCleanSession(true);
            connectionOpts.setAutomaticReconnect(true);
            connectionOpts.setUserName(ttnAppID);
            connectionOpts.setPassword(ttnAppKey.toCharArray());
            connectionOpts.setWill("/will", "Client got disconnected suddently".getBytes(), 2, true);

            logger.log(Level.INFO, "Initiating Client Instance");
            mqttClient = new MqttClient(broker, clientId, persistence);

            logger.log(Level.INFO, "Setting Callback");
            this.dispatchMap = new HashMap<>();
            dispatchMap.put("v3/" + ttnAppID + "/devices/+/join", new JoinMessageProcessor());
            dispatchMap.put("v3/" + ttnAppID + "/devices/+/up", new UplinkMessageProcessor());
            dispatchMap.put("v3/" + ttnAppID + "/devices/+/down/#", new DownlinkMessageProcessor());
            mqttClient.setCallback(new ClientCallback(this.dispatchMap));

        } catch (MqttException e) {
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, "Cause: " + e.getCause());
        }
    }

    /**
     * Method to connect the Client to the Broker
     * This Method shall be used in the case when not using loop_forever
     *
     * @return true if connection was established, false if not.
     */
    public boolean connect() {
        try {
            if (mqttClient == null) {
                System.out.println(TAG + "MQTTClient is null !");
                return false;
            } else if (!connectionStatus) {
                logger.log(Level.INFO,TAG + "Trying to connect..");
                IMqttToken iMqttToken = mqttClient.connectWithResult(connectionOpts);
                iMqttToken.waitForCompletion();
                boolean connectResponse = iMqttToken.getSessionPresent();
                logger.log(Level.INFO,TAG + "Connection status: " + connectResponse);
                connectionStatus = connectResponse;
                return connectResponse;
            }
            return false;
        } catch (MqttException e) {
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, "Cause: " + e.getCause());
            connectionStatus = false;
            return false;
        }
    }

    /**
     * This method checks the mqtt client connection and subscribes to a topic
     * @param topic The topic to be subscribed to
     * @param qos The quality of service
     */
    public void subscribe(String topic, int qos) {
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, qos);

                logger.log(Level.INFO,TAG + " Subscribed to " + topic);
            } catch (MqttException e) {
                logger.log(Level.SEVERE, e.getMessage());
                logger.log(Level.SEVERE, "Cause: " + e.getCause());
                disconnect();
                close();
            }
        }
    }

    /**
     * This method checks the mqtt client connection and subscribes to a topic
     * @param qos The quality of service
     */
    public void subscribeAll(int qos) {
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                // iterate to subscribe to the different topics
                for (String topic : topicFilter) {
                    mqttClient.subscribe(topic, qos);
                }

                logger.log(Level.INFO,TAG + " Subscribed to " + topicFilter);
            } catch (MqttException e) {
                logger.log(Level.SEVERE, e.getMessage());
                logger.log(Level.SEVERE, "Cause: " + e.getCause());
                disconnect();
                close();
            }
        }
    }

    /**
     * This Method pubishes a specific msg on a specific topic.
     *
     * @param topic The topic where the msg should be publiched
     * @param msg   The Payload that should be published
     */
    public synchronized void publish(String topic, String msg, int qos) {
        if (qos < 0 || qos > 2) {
            System.out.println(TAG + "Invalid QoS: " + qos);
            // printHelp();
            return;
        }
        if (mqttClient != null && mqttClient.isConnected()) {
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setQos(qos);
            System.out.println(TAG + " Publishing message: " + msg);
            try {
                //mqttClient.connect(connOpts);
                mqttClient.publish(topic, message);
                //mqttClient.disconnect();
            } catch (MqttException e) {
                logger.log(Level.SEVERE,TAG + "Mqtt Exeption thrown");
                logger.log(Level.SEVERE, e.getMessage());
                logger.log(Level.SEVERE, "Cause: " + e.getCause());
                disconnect();
                close();
            }
        } else {
            System.out.println(TAG + "Connection is lost or client is null");
        }
    }

    /**
     * This Method disconnects the Client from the Broker and throws an exception in case smth wrong happened.
     */
    private void disconnect() {
        try {
            System.out.println(TAG + "Disconnecting..");
            mqttClient.disconnect();
            connectionStatus = false;

        } catch (MqttException e) {
            logger.log(Level.SEVERE,TAG + "Exception when trying to disconnect the Mqtt Client");
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, "Cause: " + e.getCause());
        }
    }

    /**
     * This method disconnects the client and closes the Connection from the mqtt client to the server
     */
    public void close() {
        if (connectionStatus) {
            disconnect();
            try {
                logger.log(Level.INFO,TAG + "Closing Client..");
                mqttClient.close();
            } catch (MqttException e) {
                logger.log(Level.SEVERE,TAG + "Exception when trying to close the Mqtt Client");
                logger.log(Level.SEVERE, e.getMessage());
                logger.log(Level.SEVERE, "Cause: " + e.getCause());
            }
        } else {
            try {
                mqttClient.close();
            } catch (MqttException e) {
                System.out.println("Exception when trying to close the client");
                logger.log(Level.SEVERE, e.getMessage());
                logger.log(Level.SEVERE, "Cause: " + e.getCause());
            }
        }
    }

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

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTtnAppID() {
        return ttnAppID;
    }

    public void setTtnAppID(String ttnAppID) {
        this.ttnAppID = ttnAppID;
    }

    public String getTtnAppKey() {
        return ttnAppKey;
    }

    public void setTtnAppKey(String ttnAppKey) {
        this.ttnAppKey = ttnAppKey;
    }

    public String[] getTopicFilter() {
        return topicFilter;
    }

    public void setTopicFilter(String[] topicFilter) {
        this.topicFilter = topicFilter;
    }

    public MemoryPersistence getPersistence() {
        return persistence;
    }

    public void setPersistence(MemoryPersistence persistence) {
        this.persistence = persistence;
    }

    public MqttConnectOptions getConnectionOpts() {
        return connectionOpts;
    }

    public void setConnectionOpts(MqttConnectOptions connectionOpts) {
        this.connectionOpts = connectionOpts;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public MqttClient getMqttClient() {
        return mqttClient;
    }

    public void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }
}
