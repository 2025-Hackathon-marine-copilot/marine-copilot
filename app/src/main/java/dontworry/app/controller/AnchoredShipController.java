package dontworry.app.controller;

import dontworry.app.domain.Ship;
import dontworry.app.model.AnchoredShipDTO;
import dontworry.app.repos.ShipRepository;
import dontworry.app.service.AnchoredShipService;
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
@RequestMapping("/anchoredShips")
public class AnchoredShipController {

    private final AnchoredShipService anchoredShipService;
    private final ShipRepository shipRepository;

    public AnchoredShipController(final AnchoredShipService anchoredShipService,
            final ShipRepository shipRepository) {
        this.anchoredShipService = anchoredShipService;
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
        model.addAttribute("anchoredShips", anchoredShipService.findAll());
        return "anchoredShip/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("anchoredShip") final AnchoredShipDTO anchoredShipDTO) {
        return "anchoredShip/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("anchoredShip") @Valid final AnchoredShipDTO anchoredShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "anchoredShip/add";
        }
        anchoredShipService.create(anchoredShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("anchoredShip.create.success"));
        return "redirect:/anchoredShips";
    }

    @GetMapping("/edit/{anchoredSeqId}")
    public String edit(@PathVariable(name = "anchoredSeqId") final Long anchoredSeqId,
            final Model model) {
        model.addAttribute("anchoredShip", anchoredShipService.get(anchoredSeqId));
        return "anchoredShip/edit";
    }

    @PostMapping("/edit/{anchoredSeqId}")
    public String edit(@PathVariable(name = "anchoredSeqId") final Long anchoredSeqId,
            @ModelAttribute("anchoredShip") @Valid final AnchoredShipDTO anchoredShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "anchoredShip/edit";
        }
        anchoredShipService.update(anchoredSeqId, anchoredShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("anchoredShip.update.success"));
        return "redirect:/anchoredShips";
    }

    @PostMapping("/delete/{anchoredSeqId}")
    public String delete(@PathVariable(name = "anchoredSeqId") final Long anchoredSeqId,
            final RedirectAttributes redirectAttributes) {
        anchoredShipService.delete(anchoredSeqId);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("anchoredShip.delete.success"));
        return "redirect:/anchoredShips";
    }

}
