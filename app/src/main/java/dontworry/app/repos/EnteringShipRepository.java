package dontworry.app.repos;

import dontworry.app.domain.EnteringShip;
import dontworry.app.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnteringShipRepository extends JpaRepository<EnteringShip, Long> {

    EnteringShip findFirstByShipSeqId(Ship ship);

}
