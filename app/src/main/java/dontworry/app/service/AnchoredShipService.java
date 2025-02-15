package dontworry.app.service;

import dontworry.app.domain.AnchoredShip;
import dontworry.app.domain.ShipInfo;
import dontworry.app.domain.Ship;
import dontworry.app.model.AnchoredShipDTO;
import dontworry.app.model.ShipDTO;
import dontworry.app.repos.AnchoredShipRepository;
import dontworry.app.repos.ShipRepository;
import dontworry.app.util.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AnchoredShipService {

    private final AnchoredShipRepository anchoredShipRepository;
    private final ShipRepository shipRepository;
    private final ShipService shipService;

    public AnchoredShipService(final AnchoredShipRepository anchoredShipRepository,
            final ShipRepository shipRepository, final ShipService shipService) {
        this.anchoredShipRepository = anchoredShipRepository;
        this.shipRepository = shipRepository;
        this.shipService = shipService;
    }

    public List<AnchoredShipDTO> findAll() {
        final List<AnchoredShip> anchoredShips = anchoredShipRepository.findAll(Sort.by("anchoredSeqId"));
        return anchoredShips.stream()
                .map(anchoredShip -> mapToDTO(anchoredShip, new AnchoredShipDTO()))
                .toList();
    }

    public List<ShipInfo> findAllAnchoredShipInfo(){
        List<AnchoredShip> anchoredShipList = anchoredShipRepository.findAll();
        List<ShipInfo> shipInfoList = new ArrayList<>();
        for (AnchoredShip anchoredShip : anchoredShipList) {
            ShipDTO shipDTO = shipService.get(anchoredShip.getShipSeqId().getShipSeqId());
            ShipInfo shipInfo = new ShipInfo();
            shipInfo.setImoNumber(shipDTO.getIMONumber());
            shipInfo.setName(shipDTO.getName());
            shipInfo.setWidth(shipDTO.getWidth());
            shipInfo.setHeight(shipDTO.getHeight());
            shipInfo.setLat(anchoredShip.getX());
            shipInfo.setLng(anchoredShip.getY());
            shipInfoList.add(shipInfo);
        }
        return shipInfoList;
    }

    public AnchoredShipDTO get(final Long anchoredSeqId) {
        return anchoredShipRepository.findById(anchoredSeqId)
                .map(anchoredShip -> mapToDTO(anchoredShip, new AnchoredShipDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AnchoredShipDTO anchoredShipDTO) {
        final AnchoredShip anchoredShip = new AnchoredShip();
        mapToEntity(anchoredShipDTO, anchoredShip);
        return anchoredShipRepository.save(anchoredShip).getAnchoredSeqId();
    }

    public void update(final Long anchoredSeqId, final AnchoredShipDTO anchoredShipDTO) {
        final AnchoredShip anchoredShip = anchoredShipRepository.findById(anchoredSeqId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(anchoredShipDTO, anchoredShip);
        anchoredShipRepository.save(anchoredShip);
    }

    public void delete(final Long anchoredSeqId) {
        anchoredShipRepository.deleteById(anchoredSeqId);
    }

    private AnchoredShipDTO mapToDTO(final AnchoredShip anchoredShip,
            final AnchoredShipDTO anchoredShipDTO) {
        anchoredShipDTO.setAnchoredSeqId(anchoredShip.getAnchoredSeqId());
        anchoredShipDTO.setX(anchoredShip.getX());
        anchoredShipDTO.setY(anchoredShip.getY());
        anchoredShipDTO.setShipSeqId(anchoredShip.getShipSeqId() == null ? null : anchoredShip.getShipSeqId().getShipSeqId());
        return anchoredShipDTO;
    }

    private AnchoredShip mapToEntity(final AnchoredShipDTO anchoredShipDTO,
            final AnchoredShip anchoredShip) {
        anchoredShip.setX(anchoredShipDTO.getX());
        anchoredShip.setY(anchoredShipDTO.getY());
        final Ship shipSeqId = anchoredShipDTO.getShipSeqId() == null ? null : shipRepository.findById(anchoredShipDTO.getShipSeqId())
                .orElseThrow(() -> new NotFoundException("shipSeqId not found"));
        anchoredShip.setShipSeqId(shipSeqId);
        return anchoredShip;
    }

}
