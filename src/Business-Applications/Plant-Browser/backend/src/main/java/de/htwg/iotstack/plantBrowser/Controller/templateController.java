package de.htwg.iotstack.plantBrowser.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class templateController {

    @RequestMapping("/template")
    public String templateGet(){
        return "OK";
    }
}
