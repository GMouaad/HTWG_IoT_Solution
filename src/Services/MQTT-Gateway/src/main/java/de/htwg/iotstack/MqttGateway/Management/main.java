package de.htwg.iotstack.MqttGateway.Management;

import de.htwg.iotstack.MqttGateway.Configuration.Configuration;
import de.htwg.iotstack.MqttGateway.Util.Banner;
import de.htwg.iotstack.MqttGateway.Util.Logging;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    private static Logger logger;
    private static Configuration configuration;
    private static Manager manager;

    /**
     *  This is the Main function acting as en entry of the application
     * @param args
     */
    public static void main(String[] args) {

        if( args.length != 1 ) {
            System.out.println("Starting with default config");
            System.out.println("Use otherwise: java -jar MQTT-Gateway.jar <file.yml>");
            configuration = setup();  // "config.yml"
            logger.log(Level.INFO, configuration.toString());
        } else {
            String path = args[0];
            System.out.println("Starting with given config: "+ path);
            configuration = setup(path);
            logger.log(Level.INFO, configuration.toString());
        }

        Banner.printBanner();

        logger.info("Initiating the Instances");
        manager = new Manager(configuration);

        logger.log(Level.INFO,"Initializing Manager .. ");
        manager.init();

        logger.log(Level.INFO,"Starting Instances .. ");
        manager.start();

    }

    private static Configuration setup(String path) {
        try (InputStream input = new FileInputStream(path)) {
            Yaml yaml = new Yaml();
            Configuration config = yaml.loadAs( input, Configuration.class );
            logger = Logging.setupLogger(config.getLoggerConfig());
            System.out.println( config.toString() );
            return config;
        } catch (Exception ex) {
            System.out.println( "Error while trying to read config file" + ex);
        }
        return null; // TODO : best practice return?
    }

    private static Configuration setup() {
        try (InputStream input = main.class.getClassLoader().getResourceAsStream("config.yml")) {
            Yaml yaml = new Yaml();
            Configuration config = yaml.loadAs( input, Configuration.class );
            logger = Logging.setupLogger(config.getLoggerConfig());
            System.out.println( config.toString() );
            return config;
        } catch (Exception ex) {
            System.out.println( "Error while trying to read config file" + ex);
        }
        return null; // TODO : best practice return?
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Logger getLogger() {
        return logger;
    }
}
