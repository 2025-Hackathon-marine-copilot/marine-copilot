package dontworry.app.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnchoredShipDTO {

    private Long anchoredSeqId;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Long shipSeqId;

}
