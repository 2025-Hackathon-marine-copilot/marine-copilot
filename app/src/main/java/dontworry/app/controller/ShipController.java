package dontworry.app.controller;

import dontworry.app.model.ShipDTO;
import dontworry.app.service.ShipService;
import dontworry.app.util.ReferencedWarning;
import dontworry.app.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(final ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("ships", shipService.findAll());
        return "ship/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("ship") final ShipDTO shipDTO) {
        return "ship/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("ship") @Valid final ShipDTO shipDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ship/add";
        }
        shipService.create(shipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ship.create.success"));
        return "redirect:/ships";
    }

    @GetMapping("/edit/{shipSeqId}")
    public String edit(@PathVariable(name = "shipSeqId") final Long shipSeqId, final Model model) {
        model.addAttribute("ship", shipService.get(shipSeqId));
        return "ship/edit";
    }

    @PostMapping("/edit/{shipSeqId}")
    public String edit(@PathVariable(name = "shipSeqId") final Long shipSeqId,
            @ModelAttribute("ship") @Valid final ShipDTO shipDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ship/edit";
        }
        shipService.update(shipSeqId, shipDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ship.update.success"));
        return "redirect:/ships";
    }

    @PostMapping("/delete/{shipSeqId}")
    public String delete(@PathVariable(name = "shipSeqId") final Long shipSeqId,
            final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = shipService.getReferencedWarning(shipSeqId);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            shipService.delete(shipSeqId);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("ship.delete.success"));
        }
        return "redirect:/ships";
    }

}
