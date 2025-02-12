package dontworry.app.rest;

import dontworry.app.model.DepartureShipDTO;
import dontworry.app.service.DepartureShipService;
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
@RequestMapping(value = "/api/departureShips", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartureShipResource {

    private final DepartureShipService departureShipService;

    public DepartureShipResource(final DepartureShipService departureShipService) {
        this.departureShipService = departureShipService;
    }

    @GetMapping
    public ResponseEntity<List<DepartureShipDTO>> getAllDepartureShips() {
        return ResponseEntity.ok(departureShipService.findAll());
    }

    @GetMapping("/{departureSeqId}")
    public ResponseEntity<DepartureShipDTO> getDepartureShip(
            @PathVariable(name = "departureSeqId") final Long departureSeqId) {
        return ResponseEntity.ok(departureShipService.get(departureSeqId));
    }

    @PostMapping
    public ResponseEntity<Long> createDepartureShip(
            @RequestBody @Valid final DepartureShipDTO departureShipDTO) {
        final Long createdDepartureSeqId = departureShipService.create(departureShipDTO);
        return new ResponseEntity<>(createdDepartureSeqId, HttpStatus.CREATED);
    }

    @PutMapping("/{departureSeqId}")
    public ResponseEntity<Long> updateDepartureShip(
            @PathVariable(name = "departureSeqId") final Long departureSeqId,
            @RequestBody @Valid final DepartureShipDTO departureShipDTO) {
        departureShipService.update(departureSeqId, departureShipDTO);
        return ResponseEntity.ok(departureSeqId);
    }

    @DeleteMapping("/{departureSeqId}")
    public ResponseEntity<Void> deleteDepartureShip(
            @PathVariable(name = "departureSeqId") final Long departureSeqId) {
        departureShipService.delete(departureSeqId);
        return ResponseEntity.noContent().build();
    }

}
