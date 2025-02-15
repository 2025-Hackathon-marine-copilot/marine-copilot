import Coord;
import Grid;

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
        public static Grid coordToGrid(Coord grid)
        {
                double lat_gap = (coord.lat - MIN_LAT) / SIZE_GRID;
                double lon_gap = (MAX_LON - coord.lon) / SIZE_GRID;

                return new Grid((int) Math.round(lon_gap), (int) Math.round(lat_gap));
        }
}
