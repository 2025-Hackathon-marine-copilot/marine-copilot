package dontworry.app.controller;

import dontworry.app.domain.CurrentShip;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/map")
public class MapController {
    @GetMapping
    public String showMap(@ModelAttribute("currentShip") CurrentShip currentShip, Model model){
        if (currentShip != null) {
            model.addAttribute("shipLatitude", currentShip.getLatitude());
            model.addAttribute("shipLongitude", currentShip.getLongitude());
            model.addAttribute("shipName", currentShip.getShipName());
        }
        return "map/map";
    }
}
