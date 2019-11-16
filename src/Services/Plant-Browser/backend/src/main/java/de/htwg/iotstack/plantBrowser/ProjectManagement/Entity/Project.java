package de.htwg.iotstack.plantBrowser.ProjectManagement.Entity;

import de.htwg.iotstack.plantBrowser.Auth.User;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    private int id;
    private String title;
    private String author;
    private User owner;
    private String description;
    private Date creationDate;
    private Boolean privacy;
    private String projectLeader;
    private Date estimatedDeliveryTime;			//TODO: Check/change to a convinient Data type, Check if inserting it directly in sql is more efficient
    private ArrayList<String> resources; 		//TODO: Check/change to a convinient Data Structure
    private ArrayList<String> Tasks; 			//TODO: Check/change to a convinient Data Structure

    public Project(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Date getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getTasks() {
        return Tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        Tasks = tasks;
    }
}
