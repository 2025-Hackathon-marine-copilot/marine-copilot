package dontworry.app.service;

import dontworry.app.domain.AnchoredShip;
import dontworry.app.domain.DepartureShip;
import dontworry.app.domain.EnteringShip;
import dontworry.app.domain.Ship;
import dontworry.app.model.ShipDTO;
import dontworry.app.repos.AnchoredShipRepository;
import dontworry.app.repos.DepartureShipRepository;
import dontworry.app.repos.EnteringShipRepository;
import dontworry.app.repos.ShipRepository;
import dontworry.app.util.NotFoundException;
import dontworry.app.util.ReferencedWarning;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final AnchoredShipRepository anchoredShipRepository;
    private final DepartureShipRepository departureShipRepository;
    private final EnteringShipRepository enteringShipRepository;

    public ShipService(final ShipRepository shipRepository,
            final AnchoredShipRepository anchoredShipRepository,
            final DepartureShipRepository departureShipRepository,
            final EnteringShipRepository enteringShipRepository) {
        this.shipRepository = shipRepository;
        this.anchoredShipRepository = anchoredShipRepository;
        this.departureShipRepository = departureShipRepository;
        this.enteringShipRepository = enteringShipRepository;
    }

    public List<ShipDTO> findAll() {
        final List<Ship> ships = shipRepository.findAll(Sort.by("shipSeqId"));
        return ships.stream()
                .map(ship -> mapToDTO(ship, new ShipDTO()))
                .toList();
    }

    public ShipDTO get(final Long shipSeqId) {
        return shipRepository.findById(shipSeqId)
                .map(ship -> mapToDTO(ship, new ShipDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ShipDTO shipDTO) {
        final Ship ship = new Ship();
        mapToEntity(shipDTO, ship);
        return shipRepository.save(ship).getShipSeqId();
    }

    public void update(final Long shipSeqId, final ShipDTO shipDTO) {
        final Ship ship = shipRepository.findById(shipSeqId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(shipDTO, ship);
        shipRepository.save(ship);
    }

    public void delete(final Long shipSeqId) {
        shipRepository.deleteById(shipSeqId);
    }

    private ShipDTO mapToDTO(final Ship ship, final ShipDTO shipDTO) {
        shipDTO.setShipSeqId(ship.getShipSeqId());
        shipDTO.setIMONumber(ship.getIMONumber());
        shipDTO.setName(ship.getName());
        shipDTO.setWidth(ship.getWidth());
        shipDTO.setHeight(ship.getHeight());
        return shipDTO;
    }

    private Ship mapToEntity(final ShipDTO shipDTO, final Ship ship) {
        ship.setIMONumber(shipDTO.getIMONumber());
        ship.setName(shipDTO.getName());
        ship.setWidth(shipDTO.getWidth());
        ship.setHeight(shipDTO.getHeight());
        return ship;
    }

    public boolean iMONumberExists(final String iMONumber) {
        return shipRepository.existsByiMONumberIgnoreCase(iMONumber);
    }

    public ReferencedWarning getReferencedWarning(final Long shipSeqId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Ship ship = shipRepository.findById(shipSeqId)
                .orElseThrow(NotFoundException::new);
        final AnchoredShip shipSeqIdAnchoredShip = anchoredShipRepository.findFirstByShipSeqId(ship);
        if (shipSeqIdAnchoredShip != null) {
            referencedWarning.setKey("ship.anchoredShip.shipSeqId.referenced");
            referencedWarning.addParam(shipSeqIdAnchoredShip.getAnchoredSeqId());
            return referencedWarning;
        }
        final DepartureShip shipSeqIdDepartureShip = departureShipRepository.findFirstByShipSeqId(ship);
        if (shipSeqIdDepartureShip != null) {
            referencedWarning.setKey("ship.departureShip.shipSeqId.referenced");
            referencedWarning.addParam(shipSeqIdDepartureShip.getDepartureSeqId());
            return referencedWarning;
        }
        final EnteringShip shipSeqIdEnteringShip = enteringShipRepository.findFirstByShipSeqId(ship);
        if (shipSeqIdEnteringShip != null) {
            referencedWarning.setKey("ship.enteringShip.shipSeqId.referenced");
            referencedWarning.addParam(shipSeqIdEnteringShip.getEnteringSeqId());
            return referencedWarning;
        }
        return null;
    }

    public Ship createShip(String IMONumber, String shipName, Double width, Double height){
        Ship ship = new Ship();
        ship.setIMONumber(IMONumber);
        ship.setName(shipName);
        ship.setWidth(width);
        ship.setHeight(height);
        this.shipRepository.save(ship);
        return ship;
    }

}
