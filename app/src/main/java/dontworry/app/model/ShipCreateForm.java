package dontworry.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipCreateForm {
    private String IMONumber;
    private String shipName;
    private Double width;
    private Double height;
}
