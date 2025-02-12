package dontworry.app.rest;

import dontworry.app.model.EnteringShipDTO;
import dontworry.app.service.EnteringShipService;
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
@RequestMapping(value = "/api/enteringShips", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnteringShipResource {

    private final EnteringShipService enteringShipService;

    public EnteringShipResource(final EnteringShipService enteringShipService) {
        this.enteringShipService = enteringShipService;
    }

    @GetMapping
    public ResponseEntity<List<EnteringShipDTO>> getAllEnteringShips() {
        return ResponseEntity.ok(enteringShipService.findAll());
    }

    @GetMapping("/{enteringSeqId}")
    public ResponseEntity<EnteringShipDTO> getEnteringShip(
            @PathVariable(name = "enteringSeqId") final Long enteringSeqId) {
        return ResponseEntity.ok(enteringShipService.get(enteringSeqId));
    }

    @PostMapping
    public ResponseEntity<Long> createEnteringShip(
            @RequestBody @Valid final EnteringShipDTO enteringShipDTO) {
        final Long createdEnteringSeqId = enteringShipService.create(enteringShipDTO);
        return new ResponseEntity<>(createdEnteringSeqId, HttpStatus.CREATED);
    }

    @PutMapping("/{enteringSeqId}")
    public ResponseEntity<Long> updateEnteringShip(
            @PathVariable(name = "enteringSeqId") final Long enteringSeqId,
            @RequestBody @Valid final EnteringShipDTO enteringShipDTO) {
        enteringShipService.update(enteringSeqId, enteringShipDTO);
        return ResponseEntity.ok(enteringSeqId);
    }

    @DeleteMapping("/{enteringSeqId}")
    public ResponseEntity<Void> deleteEnteringShip(
            @PathVariable(name = "enteringSeqId") final Long enteringSeqId) {
        enteringShipService.delete(enteringSeqId);
        return ResponseEntity.noContent().build();
    }

}
