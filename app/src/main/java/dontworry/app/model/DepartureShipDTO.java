package dontworry.app.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartureShipDTO {

    private Long departureSeqId;

    @NotNull
    private Long shipSeqId;

}
