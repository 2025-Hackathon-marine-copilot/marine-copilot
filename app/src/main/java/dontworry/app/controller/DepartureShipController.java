package dontworry.app.controller;

import dontworry.app.domain.Ship;
import dontworry.app.model.DepartureShipDTO;
import dontworry.app.repos.ShipRepository;
import dontworry.app.service.DepartureShipService;
import dontworry.app.util.CustomCollectors;
import dontworry.app.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/departureShips")
public class DepartureShipController {

    private final DepartureShipService departureShipService;
    private final ShipRepository shipRepository;

    public DepartureShipController(final DepartureShipService departureShipService,
            final ShipRepository shipRepository) {
        this.departureShipService = departureShipService;
        this.shipRepository = shipRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("shipSeqIdValues", shipRepository.findAll(Sort.by("shipSeqId"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Ship::getShipSeqId, Ship::getIMONumber)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("departureShips", departureShipService.findAll());
        return "departureShip/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("departureShip") final DepartureShipDTO departureShipDTO) {
        return "departureShip/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("departureShip") @Valid final DepartureShipDTO departureShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "departureShip/add";
        }
        departureShipService.create(departureShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("departureShip.create.success"));
        return "redirect:/departureShips";
    }

    @GetMapping("/edit/{departureSeqId}")
    public String edit(@PathVariable(name = "departureSeqId") final Long departureSeqId,
            final Model model) {
        model.addAttribute("departureShip", departureShipService.get(departureSeqId));
        return "departureShip/edit";
    }

    @PostMapping("/edit/{departureSeqId}")
    public String edit(@PathVariable(name = "departureSeqId") final Long departureSeqId,
            @ModelAttribute("departureShip") @Valid final DepartureShipDTO departureShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "departureShip/edit";
        }
        departureShipService.update(departureSeqId, departureShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("departureShip.update.success"));
        return "redirect:/departureShips";
    }

    @PostMapping("/delete/{departureSeqId}")
    public String delete(@PathVariable(name = "departureSeqId") final Long departureSeqId,
            final RedirectAttributes redirectAttributes) {
        departureShipService.delete(departureSeqId);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("departureShip.delete.success"));
        return "redirect:/departureShips";
    }

}
