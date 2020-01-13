package de.htwg.iotstack.MqttGateway.Management;

import de.htwg.iotstack.MqttGateway.Communication.ClientThread;
import de.htwg.iotstack.MqttGateway.Configuration.Configuration;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager {
    private Logger logger;
    private Configuration configuration;
    private Map< String, String > applications;
    private List<ClientThread> clientThreads = new ArrayList<ClientThread>();
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
        logger.log(Level.INFO,"Initializing Clients to connect to " + configuration.getBroker().toString());
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
            logger.log(Level.INFO,ttnAppID + " w/ API-Key " + ttnAppKey);
            topicFilter = getTopicFilter(ttnAppID);
            logger.log(Level.INFO, "Topics:");
            logger.log(Level.INFO, Arrays.deepToString(topicFilter));
            try {
                clientThreads.add(new ClientThread(this.host,
                        this.port,
                        ttnAppID, // ttnAppID
                        ttnAppKey, // ttnAppKey
                        topicFilter));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception: " + e);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        //TODO: call ClientThread.init() here or Leave it in run() ??
    }

    private String[] getTopicFilter(String ttnAppID) {
        String[] topicFilter;
        topicFilter = new String[]{
                "v3/" + ttnAppID + "/devices/+/join",
                "v3/" + ttnAppID + "/devices/+/up",
                "v3/" + ttnAppID + "/devices/+/down/#"
        };
        return topicFilter;
    }

    public void start(){
        logger.log(Level.INFO,"Starting the ClientThreads");
        // iterate to start all Threads
        for (ClientThread thread: this.clientThreads) {
            thread.run();
        }
    }
}
