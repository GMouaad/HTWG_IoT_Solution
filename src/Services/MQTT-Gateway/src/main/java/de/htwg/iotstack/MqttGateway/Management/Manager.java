package de.htwg.iotstack.MqttGateway.Management;

import de.htwg.iotstack.MqttGateway.Communication.ClientThread;
import de.htwg.iotstack.MqttGateway.Configuration.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        applications = configuration.getApplications();

        logger.log(Level.INFO,"Available Applications:\n");
        // iterate to initialize Theads
        // ref https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        // for more options or efficiency comparison
        Iterator it = applications.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            logger.log(Level.INFO,pair.getKey() + " w/ API-Key " + pair.getValue());
            /* clientThreads.add(new ClientThread(host,
                    port,
                    "todo:random"+i,
                    "",
                    applications.get("app1"),
                    "v3/app1")); */
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
