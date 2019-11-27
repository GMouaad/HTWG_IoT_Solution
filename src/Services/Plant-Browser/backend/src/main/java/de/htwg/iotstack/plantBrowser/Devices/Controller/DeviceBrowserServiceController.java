package de.htwg.iotstack.plantBrowser.Devices.Controller;

import de.htwg.iotstack.plantBrowser.Devices.Entity.Device;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/device-mgmt")
public class DeviceBrowserServiceController {


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/Devices",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Device> getDevices(){
        List<Device> Devices = new ArrayList<Device>();
        Device device1 = new Device("Temperature Sensor", 1, "Prof. Dr. Müller");
        Device device2 = new Device("Humidity Sensor", 1, "Mouaad Gssair");
        device1.setId(464);
        device2.setId(574);
        device1.setUnit("celcius");
        device2.setUnit("bar");
        device1.setLastLocation(47.6675354, 9.1707902);
        device2.setLastLocation(47.6670429, 9.171882);
        Devices.add(device1);
        Devices.add(device2);
        return Devices;
    }

}
