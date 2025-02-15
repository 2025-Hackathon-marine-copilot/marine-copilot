package dontworry.app.repos;

import dontworry.app.domain.MovingShip;
import dontworry.app.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovingShipRepository extends JpaRepository<MovingShip, Long> {
    MovingShip findFirstByShipSeqId(Ship ship);
}
