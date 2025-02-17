package dontworry.app.util.coords_java;

import java.util.ArrayList;

/**
 * This class is deprecated
 */
public class Coords
{
        public static Coord gridToCoord(Grid grid)
        {
                return grid.toCoord();
        }

        public static Grid coordToGrid(Coord coord)
        {
                return coord.toGrid();
        }

        public static ArrayList<Coord> gridArrayToCoordArray(ArrayList<Grid> gridArray)
        {
                return Coordination.gridArrayToCoordArray(gridArray);
        }

        public static ArrayList<Grid> coordArrayToGridArray(ArrayList<Coord> coordArray)
        {
                return Coordination.coordArrayToGridArray(coordArray);
        }
}
