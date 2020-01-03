package de.htwg.iotstack.MqttGateway.Configuration;

import java.util.Date;
import java.util.Map;

import static java.lang.String.format;

public class Configuration {
    private Date released;
    private String version;
    private LoggerConfig loggerConfig;
    private Broker broker;
    private Map< String, String > applications;

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LoggerConfig getLoggerConfig() {
        return loggerConfig;
    }

    public void setLoggerConfig(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public Broker getBroker() {
        return this.broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Map<String, String> getApplications() {
        return applications;
    }

    public void setApplications(Map<String, String> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Configuration:\n" +
                new StringBuilder()
                        .append( format( "Version: %s\n", version ) )
                        .append( format( "Released: %s\n", released ) )
                        .append( loggerConfig.toString() )
                        .append( broker.toString() )
                        .append( format( "Applications: %s\n", applications ) )
                        .toString();
    }
}
