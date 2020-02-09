package de.htwg.iotstack.MqttGateway.Persistence;

import de.htwg.iotstack.MqttGateway.Configuration.Database;
import de.htwg.iotstack.MqttGateway.Management.main;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDBIOException;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DBController {

    private static DBController instance = null;

    private Logger logger = null;
    private Database dbConfig = null;

    private static InfluxDB influxDB;
    private String url = null;
    private String userName = null;
    private String password = null;
    private String dbName = "demo";
    private String rpName = "defaultRetentionPolicy";

    private DBController() {
        this.logger = main.getLogger();
        this.dbConfig = main.getConfiguration().getDatabase();
    }

    public static synchronized DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
            return instance;
        } else return instance;
    }

    public void setup() {
        this.dbName = dbConfig.getName();
        this.url = "http://" + dbConfig.getHost() + ":" + dbConfig.getPort();
        this.userName = dbConfig.getUsername();
        this.password = dbConfig.getPassword();

        influxDB = connectDatabase();
        pingServer(influxDB);

        // Create " and check for it
        influxDB.query(new Query("CREATE DATABASE " + dbName));
        logger.log(Level.INFO,"Created Database: " + dbName);
        influxDB.setDatabase(dbName); // i.e. Use the DB ?
        logger.log(Level.INFO,"Using Database: " + dbName);


        influxDB.query(new Query("CREATE RETENTION POLICY " + rpName + " ON " + dbName + " DURATION 30h REPLICATION 2 SHARD DURATION 30m DEFAULT"));
        logger.log(Level.INFO,"Created Retention Policy: " + rpName);
        influxDB.setRetentionPolicy(rpName);

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        logger.log(Level.INFO,"Set DB Log Level to BASIC");

        influxDB.enableBatch(BatchOptions.DEFAULTS);

    }

    public InfluxDB connectDatabase() {
        logger.log(Level.INFO,"Creating Connection to InfluxDB ");
        // return InfluxDBFactory.connect("http://127.0.0.1:8086", "admin", "admin");
        return InfluxDBFactory.connect(url, userName, password);
    }

    private boolean pingServer(InfluxDB influxDB) {
        try {
            // Ping and check for version string
            Pong response = influxDB.ping();
            if (response.getVersion().equalsIgnoreCase("unknown")) {
                logger.log(Level.INFO,"Error pinging server.");
                return false;
            } else {
                logger.log(Level.INFO,"Database version: "+ response.getVersion());
                return true;
            }
        } catch (InfluxDBIOException idbo) {
            logger.log(Level.INFO,"Exception while pinging database: "+ idbo);
            return false;
        }
    }

    public synchronized void  persist(Object object) {
        Point point = Point.measurementByPOJO(object.getClass()).addFieldsFromPOJO(object).build();
        logger.log(Level.INFO,"Writing data point to DB " + object.toString());
        influxDB.write(dbName, rpName, point);
    }

    public void deleteDB(String dbName, String rpName) {
        influxDB.query(new Query("DROP RETENTION POLICY " + rpName + " ON " + dbName));
        logger.log(Level.INFO,"Deleted Retention Policy: " + rpName);
        influxDB.query(new Query("DROP DATABASE " + dbName));
        logger.log(Level.INFO,"Deleted DB: " + dbName);
    }

    public void deleteDB() {
        influxDB.query(new Query("DROP RETENTION POLICY " + rpName + " ON " + dbName));
        logger.log(Level.INFO,"Deleted Retention Policy: " + rpName);
        influxDB.query(new Query("DROP DATABASE " + dbName));
        logger.log(Level.INFO,"Deleted DB: " + dbName);
    }

    public void closeConnection() {
        influxDB.close();
        logger.log(Level.INFO,"Connection closed");
    }
}

