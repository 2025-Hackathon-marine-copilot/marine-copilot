package dontworry.app.service;

import dontworry.app.domain.MovingShip;
import dontworry.app.domain.ShipInfo;
import dontworry.app.model.ShipDTO;
import dontworry.app.repos.MovingShipRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovingShipService {
    private final MovingShipRepository movingShipRepository;
    private final ShipService shipService;

    public MovingShipService(final MovingShipRepository movingShipRepository, final ShipService shipService){
        this.movingShipRepository = movingShipRepository;
        this.shipService = shipService;
    }

    public List<ShipInfo> findAllMovingShipInfo(){
        List<MovingShip> movingShipList = movingShipRepository.findAll();
        List<ShipInfo> shipInfoList = new ArrayList<>();
        for (MovingShip movingShip : movingShipList) {
            ShipDTO shipDTO = shipService.get(movingShip.getShipSeqId().getShipSeqId());
            ShipInfo shipInfo = new ShipInfo();
            shipInfo.setImoNumber(shipDTO.getIMONumber());
            shipInfo.setName(shipDTO.getName());
            shipInfo.setWidth(shipDTO.getWidth());
            shipInfo.setHeight(shipDTO.getHeight());
            shipInfo.setLat(movingShip.getLat());
            shipInfo.setLng(movingShip.getLng());
            shipInfoList.add(shipInfo);
        }
        return shipInfoList;
    }
}
