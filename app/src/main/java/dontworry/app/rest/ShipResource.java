package dontworry.app.rest;

import dontworry.app.model.ShipDTO;
import dontworry.app.service.ShipService;
import dontworry.app.util.ReferencedException;
import dontworry.app.util.ReferencedWarning;
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
@RequestMapping(value = "/api/ships", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipResource {

    private final ShipService shipService;

    public ShipResource(final ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public ResponseEntity<List<ShipDTO>> getAllShips() {
        return ResponseEntity.ok(shipService.findAll());
    }

    @GetMapping("/{shipSeqId}")
    public ResponseEntity<ShipDTO> getShip(@PathVariable(name = "shipSeqId") final Long shipSeqId) {
        return ResponseEntity.ok(shipService.get(shipSeqId));
    }

    @PostMapping
    public ResponseEntity<Long> createShip(@RequestBody @Valid final ShipDTO shipDTO) {
        final Long createdShipSeqId = shipService.create(shipDTO);
        return new ResponseEntity<>(createdShipSeqId, HttpStatus.CREATED);
    }

    @PutMapping("/{shipSeqId}")
    public ResponseEntity<Long> updateShip(@PathVariable(name = "shipSeqId") final Long shipSeqId,
            @RequestBody @Valid final ShipDTO shipDTO) {
        shipService.update(shipSeqId, shipDTO);
        return ResponseEntity.ok(shipSeqId);
    }

    @DeleteMapping("/{shipSeqId}")
    public ResponseEntity<Void> deleteShip(@PathVariable(name = "shipSeqId") final Long shipSeqId) {
        final ReferencedWarning referencedWarning = shipService.getReferencedWarning(shipSeqId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        shipService.delete(shipSeqId);
        return ResponseEntity.noContent().build();
    }

}
