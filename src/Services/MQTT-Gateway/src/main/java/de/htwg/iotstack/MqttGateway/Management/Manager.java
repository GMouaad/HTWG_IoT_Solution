package de.htwg.iotstack.MqttGateway.Management;

import de.htwg.iotstack.MqttGateway.Communication.ClientThread;
import de.htwg.iotstack.MqttGateway.Configuration.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager {
    private Logger logger;
    private Configuration configuration;
    private Map< String, String > applications;
    private List<ClientThread> clientThreads;
    private String host;
    private String port;


    public Manager(Configuration configuration) {
        this.configuration = configuration;
        this.logger = main.getLogger();
    }

    public void init(){
        this.applications = configuration.getApplications();
        this.host = configuration.getBroker().getHost();
        this.port = configuration.getBroker().getPort();
        String[] topicFilter = null;
        logger.log(Level.INFO,"Available Applications:\n");
        // iterate to initialize Theads
        // ref https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        // for more options or efficiency comparison
        Iterator it = applications.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String ttnAppID = pair.getKey().toString();
            String ttnAppKey = pair.getValue().toString();
            logger.log(Level.INFO,pair.getKey() + " w/ API-Key " + pair.getValue());
            topicFilter = new String[]{
                    "v3/" + ttnAppID + "/devices/+/join",
                    "v3/" + ttnAppID + "/devices/+/up",
                    "v3/" + ttnAppID + "/devices/+/down/#"
            };
            clientThreads.add(new ClientThread(host,
                    port,
                    "MQTT-Gateway--" + UUID.randomUUID().toString(),
                    ttnAppID, // ttnAppID
                    ttnAppKey, // ttnAppKey
                    topicFilter));
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
