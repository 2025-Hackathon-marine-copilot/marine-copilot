package dontworry.app.controller;

import dontworry.app.domain.Ship;
import dontworry.app.model.EnteringShipDTO;
import dontworry.app.repos.ShipRepository;
import dontworry.app.service.EnteringShipService;
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
@RequestMapping("/enteringShips")
public class EnteringShipController {

    private final EnteringShipService enteringShipService;
    private final ShipRepository shipRepository;

    public EnteringShipController(final EnteringShipService enteringShipService,
            final ShipRepository shipRepository) {
        this.enteringShipService = enteringShipService;
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
        model.addAttribute("enteringShips", enteringShipService.findAll());
        return "enteringShip/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("enteringShip") final EnteringShipDTO enteringShipDTO) {
        return "enteringShip/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("enteringShip") @Valid final EnteringShipDTO enteringShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "enteringShip/add";
        }
        enteringShipService.create(enteringShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("enteringShip.create.success"));
        return "redirect:/enteringShips";
    }

    @GetMapping("/edit/{enteringSeqId}")
    public String edit(@PathVariable(name = "enteringSeqId") final Long enteringSeqId,
            final Model model) {
        model.addAttribute("enteringShip", enteringShipService.get(enteringSeqId));
        return "enteringShip/edit";
    }

    @PostMapping("/edit/{enteringSeqId}")
    public String edit(@PathVariable(name = "enteringSeqId") final Long enteringSeqId,
            @ModelAttribute("enteringShip") @Valid final EnteringShipDTO enteringShipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "enteringShip/edit";
        }
        enteringShipService.update(enteringSeqId, enteringShipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("enteringShip.update.success"));
        return "redirect:/enteringShips";
    }

    @PostMapping("/delete/{enteringSeqId}")
    public String delete(@PathVariable(name = "enteringSeqId") final Long enteringSeqId,
            final RedirectAttributes redirectAttributes) {
        enteringShipService.delete(enteringSeqId);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("enteringShip.delete.success"));
        return "redirect:/enteringShips";
    }

}
