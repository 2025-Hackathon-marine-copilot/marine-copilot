package dontworry.app.repos;

import dontworry.app.domain.AnchoredShip;
import dontworry.app.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnchoredShipRepository extends JpaRepository<AnchoredShip, Long> {

    AnchoredShip findFirstByShipSeqId(Ship ship);

}
