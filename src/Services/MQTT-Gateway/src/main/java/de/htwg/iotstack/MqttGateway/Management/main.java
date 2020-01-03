package de.htwg.iotstack.MqttGateway.Management;

import de.htwg.iotstack.MqttGateway.Configuration.Configuration;
import de.htwg.iotstack.MqttGateway.Util.Banner;
import de.htwg.iotstack.MqttGateway.Util.Logging;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class main {

    private static Logger logger;

    /**
     *  This is the Main function acting as en entry of the application
     * @param args
     */
    public static void main(String[] args) {

        if( args.length != 1 ) {
            //System.out.println( "Usage: <file.yml>" );
            System.out.println("Starting with default config");
            System.out.println("Use otherwise: java -jar MQTT-Gateway.jar <file.yml>");
            URL res = main.class.getClassLoader().getResource("config.yml");
            File file = null;
            try {
                file = Paths.get(res.toURI()).toFile();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String absolutePath = file.getAbsolutePath();
            setup(absolutePath);
        } else {
            String path = args[0];
            System.out.println("Starting with given config: "+ path);
        }

        Banner.printBanner();

        logger.info("Initiating the Instances");



        /*
        ComController comController = new ComController("ea-pc165.ei.htwg-konstanz.de", "8883", "AutonomV1");

        DataPersistanceThread dataPersistanceThread = new DataPersistanceThread(5000, comController, logger);
        dataPersistanceThread.initProperties();

        ManagementThread managementThread = new ManagementThread(comController, logger);
        comController.init("/SysArch/V1/Driver/AuthResponse/", true, "V1", "DE1");
        */
        //logger.info("Starting the Threads");
        //managementThread.start();
        //dataPersistanceThread.start();

    }

    private static void setup(String path) {
        try (InputStream input = new FileInputStream(path)) {
            Yaml yaml = new Yaml();
            Configuration config = yaml.loadAs( input, Configuration.class );
            logger = Logging.setupLogger(config.getLoggerConfig());
            System.out.println( config.toString() );
        } catch (Exception ex) {
            //logger.log(Level.SEVERE, "Error while trying to read config file", ex);
            System.out.println("Error while trying to read config file" + ex);
        }
    }

}
