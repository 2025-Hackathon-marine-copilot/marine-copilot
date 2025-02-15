package dontworry.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShipInfo {
    private String imoNumber;
    private String name;
    private Double width;
    private Double height;
    private Double lat;
    private Double lng;

    public ShipInfo(String imoNumber, String name, Double width, Double height, Double lat, Double lng) {
        this.imoNumber = imoNumber;
        this.name = name;
        this.width = width;
        this.height = height;
        this.lat = lat;
        this.lng = lng;
    }

    public ShipInfo(){

    }
}
