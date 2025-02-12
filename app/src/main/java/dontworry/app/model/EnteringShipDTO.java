package dontworry.app.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnteringShipDTO {

    private Long enteringSeqId;

    @NotNull
    private Long shipSeqId;

}
