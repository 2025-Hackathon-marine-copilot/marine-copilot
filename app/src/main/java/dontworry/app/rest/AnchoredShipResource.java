package dontworry.app.rest;

import dontworry.app.model.AnchoredShipDTO;
import dontworry.app.service.AnchoredShipService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/anchoredShips", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnchoredShipResource {

    private final AnchoredShipService anchoredShipService;

    public AnchoredShipResource(final AnchoredShipService anchoredShipService) {
        this.anchoredShipService = anchoredShipService;
    }

    @GetMapping
    public ResponseEntity<List<AnchoredShipDTO>> getAllAnchoredShips() {
        return ResponseEntity.ok(anchoredShipService.findAll());
    }

    @GetMapping("/{anchoredSeqId}")
    public ResponseEntity<AnchoredShipDTO> getAnchoredShip(
            @PathVariable(name = "anchoredSeqId") final Long anchoredSeqId) {
        return ResponseEntity.ok(anchoredShipService.get(anchoredSeqId));
    }

    @PostMapping
    public ResponseEntity<Long> createAnchoredShip(
            @RequestBody @Valid final AnchoredShipDTO anchoredShipDTO) {
        final Long createdAnchoredSeqId = anchoredShipService.create(anchoredShipDTO);
        return new ResponseEntity<>(createdAnchoredSeqId, HttpStatus.CREATED);
    }

    @PutMapping("/{anchoredSeqId}")
    public ResponseEntity<Long> updateAnchoredShip(
            @PathVariable(name = "anchoredSeqId") final Long anchoredSeqId,
            @RequestBody @Valid final AnchoredShipDTO anchoredShipDTO) {
        anchoredShipService.update(anchoredSeqId, anchoredShipDTO);
        return ResponseEntity.ok(anchoredSeqId);
    }

    @DeleteMapping("/{anchoredSeqId}")
    public ResponseEntity<Void> deleteAnchoredShip(
            @PathVariable(name = "anchoredSeqId") final Long anchoredSeqId) {
        anchoredShipService.delete(anchoredSeqId);
        return ResponseEntity.noContent().build();
    }

}
