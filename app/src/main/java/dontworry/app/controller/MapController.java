package dontworry.app.controller;

import dontworry.app.domain.ShipInfo;
import dontworry.app.domain.Weather;
import dontworry.app.service.AnchoredShipService;
import dontworry.app.service.MovingShipService;
import dontworry.app.service.PortWeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {
    private final AnchoredShipService anchoredShipService;
    private final MovingShipService movingShipService;
    private final PortWeatherService portWeatherService;

    public MapController(final AnchoredShipService anchoredShipService, final MovingShipService movingShipService, final PortWeatherService portWeatherService){
        this.anchoredShipService = anchoredShipService;
        this.movingShipService = movingShipService;
        this.portWeatherService = portWeatherService;
    }

    @GetMapping
    public String showMap(@ModelAttribute("currentShip") ShipInfo currentShip, Model model) throws Exception {
        List<ShipInfo> anchoredShipInfo = anchoredShipService.findAllAnchoredShipInfo();
        List<ShipInfo> movingShipInfo = movingShipService.findAllMovingShipInfo();
        Weather weatherInfo = portWeatherService.getWeatherInfo();

        model.addAttribute("anchoredShips", anchoredShipInfo);
        model.addAttribute("movingShips", movingShipInfo);
        model.addAttribute("currentShip", currentShip);
        model.addAttribute("weatherInfo", weatherInfo);

        return "map/map";
    }
}