package dontworry.app.controller;

import dontworry.app.domain.ShipInfo;
import dontworry.app.service.AnchoredShipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {
    private final AnchoredShipService anchoredShipService;

    public MapController(final AnchoredShipService anchoredShipService){
        this.anchoredShipService = anchoredShipService;
    }

    @GetMapping
    public String showMap(@ModelAttribute("currentShip") ShipInfo currentShip, Model model){
        List<ShipInfo> allShipInfo = anchoredShipService.findAllAnchoredShipInfo();

        // 📌 리스트가 null이면 빈 리스트로 대체
        if (allShipInfo == null) {
            allShipInfo = new ArrayList<>();
        }

        model.addAttribute("anchoredShips", allShipInfo);

        if (currentShip != null) {
            model.addAttribute("currentShip", currentShip);
        }
        return "map/map";
    }
}