package dontworry.app.util.coords_java;

import java.util.ArrayList;

public class Coords
{
        public static final double MIN_LAT = 35.068818;
        public static final double MAX_LON = 129.117633;
        public static final double SIZE_GRID = 0.002423;

        public static final int SIZE_X = 24;
        public static final int SIZE_Y = 16;

        /**
         * @brief x-y 좌표계를 위도-경도 좌표계로 변환하는 함수
         */
        public static Coord gridToCoord(Grid grid)
        {
                return new Coord(MAX_LON - SIZE_GRID * grid.x, MIN_LAT + SIZE_GRID * grid.y);
        }

        /**
         * @brief 위도-경도 좌표계를 x-y 좌표계로 변환하는 함수
         */
        public static Grid coordToGrid(Coord coord)
        {
                double lat_gap = (coord.lat - MIN_LAT) / SIZE_GRID;
                double lon_gap = (MAX_LON - coord.lon) / SIZE_GRID;

                return new Grid((int) Math.round(lon_gap), (int) Math.round(lat_gap));
        }

        /**
         * @brief ArrayList<Grid>를 받아 ArrayList<Coord>로 변환하는 함수
         */
        public static ArrayList<Coord> gridArrayToCoordArray(ArrayList<Grid> gridArray)
        {
                ArrayList<Coord> coordArray = new ArrayList<>();

                for(Grid g: gridArray)
                        coordArray.add(gridToCoord(g));

                return coordArray;
        }

        /**
         * @brief ArrayList<Coord>를 받아 ArrayList<Grid>로 변환하는 함수
         */
        public static ArrayList<Grid> CoordArrayToGridArray(ArrayList<Coord> coordArray)
        {
                ArrayList<Grid> gridArray = new ArrayList<>();

                for(Coord c: coordArray)
                        gridArray.add(coordToGrid(c));

                return gridArray;
        }
}
