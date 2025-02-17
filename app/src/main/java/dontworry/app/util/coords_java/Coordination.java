package dontworry.app.util.coords_java;

import java.util.ArrayList;

public class Coordination {
        public static final double MIN_LAT = 35.068818;
        public static final double MAX_LON = 129.117633;
        public static final double SIZE_GRID = 0.002423;

        public static final int SIZE_X = 25;
        public static final int SIZE_Y = 17;

        public static double navigableOcean[][] = {
                {35.091225, 129.117631},
                {35.091014, 129.099521}, 
                {35.093505, 129.094758}, 
                {35.103725, 129.094028}, 
                {35.103407, 129.074845}, 
                {35.108181, 129.067292}, 
                {35.100527, 129.059481}, 
                {35.095471, 129.070124},
                {35.085939, 129.083382},
                {35.080731, 129.096392},
                {35.068818, 129.095082},
                {35.068818, 129.117631}
        };

        /**
         * @brief ArrayList<Grid>를 받아 ArrayList<Coord>로 변환하는 함수
         */
        public static ArrayList<Coord> gridArrayToCoordArray(ArrayList<Grid> gridArray)
        {
                ArrayList<Coord> coordArray = new ArrayList<>();

                for(Grid g: gridArray)
                        coordArray.add(g.toCoord());

                return coordArray;
        }

        /**
         * @brief ArrayList<Coord>를 받아 ArrayList<Grid>로 변환하는 함수
         */
        public static ArrayList<Grid> coordArrayToGridArray(ArrayList<Coord> coordArray)
        {
                ArrayList<Grid> gridArray = new ArrayList<>();

                for(Coord c: coordArray)
                        gridArray.add(c.toGrid());

                return gridArray;
        }
}
