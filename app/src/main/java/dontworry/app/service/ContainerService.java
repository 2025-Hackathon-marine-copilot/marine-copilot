package dontworry.app.service;

import dontworry.app.domain.Container;
import dontworry.app.repos.ContainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {
    private final ContainerRepository containerRepository;

    public ContainerService(final ContainerRepository containerRepository){
        this.containerRepository = containerRepository;
    }

    public Container create(String ownerCode, String serialCode, String checkDigit, String disembarkationShipName, Integer inspectionResult){
        Container container = new Container();
        container.setOwnerCode(ownerCode);
        container.setSerialCode(serialCode);
        container.setCheckDigit(checkDigit);
        container.setDisembarkationShipName(disembarkationShipName);
        container.setInspectionResult(inspectionResult);
        this.containerRepository.save(container);
        return container;
    }

    public List<Container> findAll(){
        List<Container> containerList = containerRepository.findAll();
        return containerList;
    }
}
