package dontworry.app.service;

import dontworry.app.domain.DepartureShip;
import dontworry.app.domain.Ship;
import dontworry.app.model.DepartureShipDTO;
import dontworry.app.repos.DepartureShipRepository;
import dontworry.app.repos.ShipRepository;
import dontworry.app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DepartureShipService {

    private final DepartureShipRepository departureShipRepository;
    private final ShipRepository shipRepository;

    public DepartureShipService(final DepartureShipRepository departureShipRepository,
            final ShipRepository shipRepository) {
        this.departureShipRepository = departureShipRepository;
        this.shipRepository = shipRepository;
    }

    public List<DepartureShipDTO> findAll() {
        final List<DepartureShip> departureShips = departureShipRepository.findAll(Sort.by("departureSeqId"));
        return departureShips.stream()
                .map(departureShip -> mapToDTO(departureShip, new DepartureShipDTO()))
                .toList();
    }

    public DepartureShipDTO get(final Long departureSeqId) {
        return departureShipRepository.findById(departureSeqId)
                .map(departureShip -> mapToDTO(departureShip, new DepartureShipDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DepartureShipDTO departureShipDTO) {
        final DepartureShip departureShip = new DepartureShip();
        mapToEntity(departureShipDTO, departureShip);
        return departureShipRepository.save(departureShip).getDepartureSeqId();
    }

    public void update(final Long departureSeqId, final DepartureShipDTO departureShipDTO) {
        final DepartureShip departureShip = departureShipRepository.findById(departureSeqId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(departureShipDTO, departureShip);
        departureShipRepository.save(departureShip);
    }

    public void delete(final Long departureSeqId) {
        departureShipRepository.deleteById(departureSeqId);
    }

    private DepartureShipDTO mapToDTO(final DepartureShip departureShip,
            final DepartureShipDTO departureShipDTO) {
        departureShipDTO.setDepartureSeqId(departureShip.getDepartureSeqId());
        departureShipDTO.setShipSeqId(departureShip.getShipSeqId() == null ? null : departureShip.getShipSeqId().getShipSeqId());
        return departureShipDTO;
    }

    private DepartureShip mapToEntity(final DepartureShipDTO departureShipDTO,
            final DepartureShip departureShip) {
        final Ship shipSeqId = departureShipDTO.getShipSeqId() == null ? null : shipRepository.findById(departureShipDTO.getShipSeqId())
                .orElseThrow(() -> new NotFoundException("shipSeqId not found"));
        departureShip.setShipSeqId(shipSeqId);
        return departureShip;
    }

}
