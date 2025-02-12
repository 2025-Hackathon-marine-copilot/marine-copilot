package dontworry.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShipDTO {

    private Long shipSeqId;

    @NotNull
    @Size(max = 255)
    @ShipIMONumberUnique
    @JsonProperty("iMONumber")
    private String iMONumber;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double width;

    @NotNull
    private Double height;

}
