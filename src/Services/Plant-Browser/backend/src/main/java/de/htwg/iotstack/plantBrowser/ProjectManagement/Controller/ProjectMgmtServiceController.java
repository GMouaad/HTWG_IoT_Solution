package de.htwg.iotstack.plantBrowser.ProjectManagement.Controller;

import de.htwg.iotstack.plantBrowser.ProjectManagement.Entity.Project;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/project-mgmt")
public class ProjectMgmtServiceController {


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/Devices",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Project> getDevices(){
        List<Project> Devices = new ArrayList<Project>();
        Project project1 = new Project("Senegal Solarlampen", "IoG");
        Project project2 = new Project("Senegal Solarpumpen", "IoG");
        Devices.add(project1);
        Devices.add(project2);
        return Devices;
    }

}
