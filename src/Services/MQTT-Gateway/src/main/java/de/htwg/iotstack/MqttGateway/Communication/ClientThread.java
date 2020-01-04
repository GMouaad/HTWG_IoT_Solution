package de.htwg.iotstack.MqttGateway.Communication;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientThread implements Runnable{
    private static final String TAG = "ClientThread " + Thread.currentThread().getId();

    private String host = "iot.eclipse.org";
    private String port = "1883";
    private String broker = "tcp://" + host + ":" + port;
    private String clientId = "a1"; //TODO: use Thread.currentThread().getId() ??
    private String ttnApp;
    private String ttnAppKey;
    private String topicFilter;
    private MemoryPersistence persistence;
    private MqttConnectOptions connectionOpts;

    private boolean connectionStatus;

    private MqttClient mqttClient;

    public ClientThread(){

    }

    public ClientThread(String host, String port, String clientId, String ttnApp, String ttnAppKey, String topicFilter) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
        this.ttnApp = ttnApp;
        this.ttnAppKey = ttnAppKey;
        this.topicFilter = topicFilter;

        persistence = new MemoryPersistence();
        connectionOpts = new MqttConnectOptions();
        connectionOpts.setCleanSession(true);
        connectionOpts.setAutomaticReconnect(true);
        connectionOpts.setUserName(ttnApp);
        connectionOpts.setPassword(ttnAppKey.toCharArray());
        connectionOpts.setWill("/lastWill", "Client got disconnected suddently".getBytes(), 2, true);
        try {
            mqttClient = new MqttClient(broker, clientId, persistence);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        mqttClient.setCallback(new ClientCallback());
        // subscribe to /SysArch/V1/Driver/AuthResponse/
        subscribe(topicFilter, 2);
    }

    @Override
    public void run() {
        connect();
    }
    //public ClientThread(String host, String port, String clientID, MqttConnectOptions connectionOpts){

    //}

    public void init(){
        try {
            persistence = new MemoryPersistence();
            connectionOpts = new MqttConnectOptions();
            connectionOpts.setCleanSession(true);
            connectionOpts.setAutomaticReconnect(true);
            connectionOpts.setUserName(ttnApp);
            connectionOpts.setPassword(ttnAppKey.toCharArray());
            connectionOpts.setWill("/will", "Client got disconnected suddently".getBytes(), 2, true);
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
                IMqttToken iMqttToken = mqttClient.connectWithResult(connectionOpts);
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

    public String getTtnApp() {
        return ttnApp;
    }

    public void setTtnApp(String ttnApp) {
        this.ttnApp = ttnApp;
    }

    public String getTtnAppKey() {
        return ttnAppKey;
    }

    public void setTtnAppKey(String ttnAppKey) {
        this.ttnAppKey = ttnAppKey;
    }

    public String getTopicFilter() {
        return topicFilter;
    }

    public void setTopicFilter(String topicFilter) {
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
