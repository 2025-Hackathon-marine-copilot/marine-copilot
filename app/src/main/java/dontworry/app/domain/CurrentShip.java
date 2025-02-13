package dontworry.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CurrentShip {
    private String shipName;
    private Double latitude;
    private Double longitude;
}
