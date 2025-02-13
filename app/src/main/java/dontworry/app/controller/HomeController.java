package dontworry.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/admin")
    public String index() {
        return "home/index";
    }

    @GetMapping("/")
    public String shipInformationTransmission(){
        return "initial/initial_page";
    }
}
