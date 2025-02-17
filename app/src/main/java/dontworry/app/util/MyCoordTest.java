package dontworry.app.util;

import dontworry.app.util.coords_java.Coord;
import dontworry.app.util.coords_java.Coords;
import dontworry.app.util.coords_java.Grid;

public class MyCoordTest {
    public static void main(String[] args) {
        Coord coord = Coords.gridToCoord(new Grid(13, 10));
        Coord coord2 = Coords.gridToCoord(new Grid(6, 11));
        Coord coord3 = Coords.gridToCoord(new Grid(13, 13));

        System.out.println(coord.getLat() + "  " + coord.getLon());
        System.out.println(coord2.getLat() + "  " + coord2.getLon());
        System.out.println(coord3.getLat() + "  " + coord3.getLon());
    }
}
