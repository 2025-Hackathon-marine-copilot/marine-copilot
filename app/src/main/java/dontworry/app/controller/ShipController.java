package dontworry.app.controller;

import dontworry.app.domain.ShipInfo;
import dontworry.app.model.ShipCreateForm;
import dontworry.app.model.ShipDTO;
import dontworry.app.myclass.RandomCoordinateGenerator;
import dontworry.app.service.ShipService;
import dontworry.app.util.ReferencedWarning;
import dontworry.app.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
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

    @GetMapping("/enroll")
    public String enroll(ShipCreateForm shipCreateForm){
        return "shipEnrollment/ship_enroll_form";
    }

    @PostMapping("/enroll")
    public String enroll(@Valid ShipCreateForm shipCreateForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "initial/initial_page";
        }
        try{
            shipService.createShip(shipCreateForm.getIMONumber(), shipCreateForm.getShipName(), shipCreateForm.getWidth(), shipCreateForm.getHeight());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("enrollFailed", "이미 등록된 선박입니다.");
            return "shipEnrollment/ship_enroll_form";
        }
        catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("enrollFailed", e.getMessage());
            return "shipEnrollment/ship_enroll_form";
        }

        RandomCoordinateGenerator randomCoordinateGenerator = new RandomCoordinateGenerator();
        double[] randomCoord = randomCoordinateGenerator.getRandomCoordinate();

        ShipInfo currentShip = new ShipInfo(shipCreateForm.getIMONumber(), shipCreateForm.getShipName(), shipCreateForm.getWidth(), shipCreateForm.getHeight(), randomCoord[0], randomCoord[1]);
        redirectAttributes.addFlashAttribute("currentShip", currentShip);

        return "redirect:/map";
    }
}
