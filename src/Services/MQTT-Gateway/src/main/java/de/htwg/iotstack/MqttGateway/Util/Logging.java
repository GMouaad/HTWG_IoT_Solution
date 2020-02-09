package de.htwg.iotstack.MqttGateway.Util;

import de.htwg.iotstack.MqttGateway.Configuration.LoggerConfig;
import de.htwg.iotstack.MqttGateway.Management.main;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This is the logging class to log some information to the console and file
 *
 * @author yannick
 *
 */
public class Logging {
    /**
     * Sets the up logger.
     *
     * @return the logger
     */
    private static Logger LOGGER;
    private static String fileName;
    private static int maxFileSize;
    private static int maxNumberOfFiles;
    private static Boolean appendFiles;
    private static Level debugLevel;

    /**
     * This will create the logger-instance
     *
     * @return
     */
    public static Logger setupLogger(LoggerConfig config) {
        LOGGER = Logger.getLogger(main.class.getName());
        FileHandler fh;

        try {
            fh = new FileHandler(config.getFileName(), config.getMaxFileSize(), config.getMaxFileSize(), config.isAppendFiles());
            LOGGER.addHandler(fh);

            fh.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] %2$s%n [%3$-7s] %4$s %n"; // add %5$s for Throwable

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),           // %1
                            lr.getSourceClassName() + " " + lr.getSourceMethodName() ,            // %2
                            lr.getLevel().getLocalizedName(),   // %3
                            lr.getMessage(),                    // %4
                            lr.getThrown());                    // %5

                }
            });
            LOGGER.setLevel(debugLevel);
            LOGGER.info("-- LOGGER stated --");
            // LOGGER.setUseParentHandlers(false);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LOGGER;
    }
}