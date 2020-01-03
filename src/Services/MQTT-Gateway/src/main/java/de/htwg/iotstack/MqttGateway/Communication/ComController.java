package de.htwg.iotstack.MqttGateway.Communication;

import de.htwg.iotstack.MqttGateway.Communication.ClientCallback;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * This Class controlls the Communication interface over MQTT
 *
 * @author Mgsair
 */
public class ComController {
    private static final String TAG = "ComController ";

    private String host = "tcp://iot.eclipse.org";
    private String port = "1883";
    private String broker = host + ":" + port;
    private String clientId = "V1";
    private MemoryPersistence persistence;
    private MqttConnectOptions connOpts;

    private MqttClient mqttClient;
    private boolean connectionStatus;

    /**
     * No args constructor for use in serialization
     */
    public ComController() {
        this.connectionStatus = false;
    }

    public ComController(String host, String port, String clientId) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
        this.broker = "tcp://" + host + ":" + port;
        this.connectionStatus = false;
    }

    /**
     * This method initializes the Communication controller with the necessary parameters
     * @param topicFilter
     * @param cleanSession
     * @param userName
     * @param password
     */
    public void init(String topicFilter, boolean cleanSession, String userName, String password) {
        try {
            persistence = new MemoryPersistence();
            connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(cleanSession);
            connOpts.setAutomaticReconnect(true);
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            connOpts.setWill("/SysArch/V1/Driver/LogoutRequest/", "Client got disconnected suddently".getBytes(), 2, true);
            mqttClient = new MqttClient(broker, clientId, persistence);
            mqttClient.setCallback(new ClientCallback());
            connect();
            // subscribe to /SysArch/V1/Driver/AuthResponse/
            subscribe(topicFilter, 2);
        } catch (MqttException e) {
            e.printStackTrace();
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
                System.out.println(TAG + "Trying to connect..");
                IMqttToken iMqttToken = mqttClient.connectWithResult(connOpts);
                iMqttToken.waitForCompletion();
                boolean connectResponse = iMqttToken.getSessionPresent();
                System.out.println(TAG + "Connection status: " + connectResponse);
                connectionStatus = connectResponse;
                return connectResponse;
            }
            return false;
        } catch (MqttException e) {
            e.printStackTrace();
            connectionStatus = false;
            return false;
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
                System.out.println(TAG + "Mqtt Exeption thrown");
                e.printStackTrace();
                disconnect();
                close();
            }
        } else {
            System.out.println(TAG + "Connection is lost or client is null");
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
                //mqttClient.connect(connOpts);
                mqttClient.subscribe(topic, qos);
                System.out.println(TAG + " Subscribed to " + topic);
                //mqttClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
                disconnect();
                close();
            }
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
            System.out.println(TAG + "Exception when trying to disconnect the Mqtt Client");
            e.printStackTrace();
        }
    }

    /**
     * This method disconnects the client and closes the Connection from the mqtt client to the server
     */
    public void close() {
        if (connectionStatus) {
            disconnect();
            try {
                System.out.println(TAG + "Closing Client..");
                mqttClient.close();
            } catch (MqttException e) {
                System.out.println(TAG + "Exception when trying to close the Mqtt Client");
                e.printStackTrace();
            }
        } else {
            try {
                mqttClient.close();
            } catch (MqttException e) {
                System.out.println("Exception when trying to close the client");
                e.printStackTrace();
            }
        }
    }

    public boolean getConnectionStatus() {
        return connectionStatus;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }
}
