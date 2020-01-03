package de.htwg.iotstack.MqttGateway.Communication;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ClientCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Lost Connection to the server !..");
        System.out.println(throwable.getStackTrace());
        System.out.println("Cause: " + throwable.getCause());

        // TODO: Log Severe Error


    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        /* Check if topic is: /V1/Driver/AuthResponse/ */
        System.out.println("Msg received on :" + topic + " :" + mqttMessage.toString());
        if (topic.equals("/SysArch/V1/Driver/AuthResponse/")) {
            // convert String to Pojo
            /*Driver allowedDriver = new Driver();
            String driverMsg = new String(mqttMessage.getPayload());
            System.out.println("Driver Json: " + driverMsg);
            allowedDriver = (Driver) Converter.json2pojo(driverMsg, allowedDriver);
            System.out.println("Driver: " + allowedDriver);
            if (!allowedDriver.getFirstName().isEmpty()){
                ManagementThread.updateDriver(true, allowedDriver);
            }
            */


        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
