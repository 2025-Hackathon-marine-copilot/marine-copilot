package dontworry.app.controller;

import dontworry.app.domain.ShipInfo;
import dontworry.app.service.AnchoredShipService;
import dontworry.app.service.MovingShipService;
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

    public MapController(final AnchoredShipService anchoredShipService, final MovingShipService movingShipService){
        this.anchoredShipService = anchoredShipService;
        this.movingShipService = movingShipService;
    }

    @GetMapping
    public String showMap(@ModelAttribute("currentShip") ShipInfo currentShip, Model model){
        List<ShipInfo> anchoredShipInfo = anchoredShipService.findAllAnchoredShipInfo();
        List<ShipInfo> movingShipInfo = movingShipService.findAllMovingShipInfo();

        model.addAttribute("anchoredShips", anchoredShipInfo);
        model.addAttribute("movingShips", movingShipInfo);
        model.addAttribute("currentShip", currentShip);

        return "map/map";
    }
}