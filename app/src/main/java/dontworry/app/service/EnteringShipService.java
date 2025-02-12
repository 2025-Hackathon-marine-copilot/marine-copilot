package dontworry.app.service;

import dontworry.app.domain.EnteringShip;
import dontworry.app.domain.Ship;
import dontworry.app.model.EnteringShipDTO;
import dontworry.app.repos.EnteringShipRepository;
import dontworry.app.repos.ShipRepository;
import dontworry.app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EnteringShipService {

    private final EnteringShipRepository enteringShipRepository;
    private final ShipRepository shipRepository;

    public EnteringShipService(final EnteringShipRepository enteringShipRepository,
            final ShipRepository shipRepository) {
        this.enteringShipRepository = enteringShipRepository;
        this.shipRepository = shipRepository;
    }

    public List<EnteringShipDTO> findAll() {
        final List<EnteringShip> enteringShips = enteringShipRepository.findAll(Sort.by("enteringSeqId"));
        return enteringShips.stream()
                .map(enteringShip -> mapToDTO(enteringShip, new EnteringShipDTO()))
                .toList();
    }

    public EnteringShipDTO get(final Long enteringSeqId) {
        return enteringShipRepository.findById(enteringSeqId)
                .map(enteringShip -> mapToDTO(enteringShip, new EnteringShipDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EnteringShipDTO enteringShipDTO) {
        final EnteringShip enteringShip = new EnteringShip();
        mapToEntity(enteringShipDTO, enteringShip);
        return enteringShipRepository.save(enteringShip).getEnteringSeqId();
    }

    public void update(final Long enteringSeqId, final EnteringShipDTO enteringShipDTO) {
        final EnteringShip enteringShip = enteringShipRepository.findById(enteringSeqId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(enteringShipDTO, enteringShip);
        enteringShipRepository.save(enteringShip);
    }

    public void delete(final Long enteringSeqId) {
        enteringShipRepository.deleteById(enteringSeqId);
    }

    private EnteringShipDTO mapToDTO(final EnteringShip enteringShip,
            final EnteringShipDTO enteringShipDTO) {
        enteringShipDTO.setEnteringSeqId(enteringShip.getEnteringSeqId());
        enteringShipDTO.setShipSeqId(enteringShip.getShipSeqId() == null ? null : enteringShip.getShipSeqId().getShipSeqId());
        return enteringShipDTO;
    }

    private EnteringShip mapToEntity(final EnteringShipDTO enteringShipDTO,
            final EnteringShip enteringShip) {
        final Ship shipSeqId = enteringShipDTO.getShipSeqId() == null ? null : shipRepository.findById(enteringShipDTO.getShipSeqId())
                .orElseThrow(() -> new NotFoundException("shipSeqId not found"));
        enteringShip.setShipSeqId(shipSeqId);
        return enteringShip;
    }

}
