package dontworry.app.controller;

import dontworry.app.domain.Container;
import dontworry.app.service.ContainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/containers")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(final ContainerService containerService){
        this.containerService = containerService;
    }

    @PostMapping("/create")
    @ResponseBody  // 🚀 JSON 응답을 반환하여 페이지 유지
    public ResponseEntity<?> create(@RequestParam("ownerCode") String ownerCode,
                                    @RequestParam("serialCode") String serialCode,
                                    @RequestParam("checkDigit") String checkDigit) {

        String disembarkationShipName = "SHIP SEUNGWU[KR]";
        Integer inspectionResult = 1;

        this.containerService.create(ownerCode, serialCode, checkDigit, disembarkationShipName, inspectionResult);
        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

    @GetMapping("/showList")
    public String showList(Model model){
        List<Container> list = containerService.findAll();
        model.addAttribute("containers", list);
        return "logistics/all_list";
    }
}
