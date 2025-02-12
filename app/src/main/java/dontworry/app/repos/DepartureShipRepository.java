package dontworry.app.repos;

import dontworry.app.domain.DepartureShip;
import dontworry.app.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartureShipRepository extends JpaRepository<DepartureShip, Long> {

    DepartureShip findFirstByShipSeqId(Ship ship);

}
