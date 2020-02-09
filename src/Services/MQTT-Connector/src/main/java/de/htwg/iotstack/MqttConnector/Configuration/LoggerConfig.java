package de.htwg.iotstack.MqttGateway.Configuration;

public class LoggerConfig {

    private String fileName;
    private int maxFileSize;
    private int maxNumberOfFiles;
    private boolean appendFiles;
    private String debugLevel;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getMaxNumberOfFiles() {
        return maxNumberOfFiles;
    }

    public void setMaxNumberOfFiles(int maxNumberOfFiles) {
        this.maxNumberOfFiles = maxNumberOfFiles;
    }

    public boolean isAppendFiles() {
        return appendFiles;
    }

    public void setAppendFiles(boolean appendFiles) {
        this.appendFiles = appendFiles;
    }

    public String getDebugLevel() {
        return debugLevel;
    }

    public void setDebugLevel(String debugLevel) {
        this.debugLevel = debugLevel;
    }

    @Override
    public String toString() {
        return "LoggerConfig: {" +
                "fileName='" + fileName + '\'' +
                ", maxFileSize=" + maxFileSize +
                ", maxNumberOfFiles=" + maxNumberOfFiles +
                ", appendFiles=" + appendFiles +
                ", debugLevel='" + debugLevel + '\'' +
                "}\n";
    }
}
