package dontworry.app.repos;

import dontworry.app.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipRepository extends JpaRepository<Ship, Long> {

    boolean existsByiMONumberIgnoreCase(String iMONumber);

}
