package de.htwg.iotstack.plantBrowser.Devices.Entity;

import de.htwg.iotstack.plantBrowser.Auth.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Device {

    private int id;
    private String name;
    private String type;    // Sensor or actuator ? /

    private String unit;
    private int plantID;
    private String owner;   // TODO: change to User
    private String description;
    private Date installationDate;
    private Date lastUpdate;
    private Location lastLocation;
    private Map<String,Object> additionlProps = new HashMap<String, Object>();

    public Device(String name, int plantID, String owner) {
        this.name = name;
        this.plantID = plantID;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void setLastLocation(double latitude, double longitude) {
        this.lastLocation = new Location(latitude,longitude);
    }

    public Map<String, Object> getAdditionlProps() {
        return additionlProps;
    }

    public void setAdditionlProps(Map<String, Object> additionlProps) {
        this.additionlProps = additionlProps;
    }
}
