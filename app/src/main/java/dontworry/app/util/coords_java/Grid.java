package dontworry.app.util.coords_java;

public class Grid
{
        private int x, y;

        public Grid(int x, int y)
        {
                this.x = x;
                this.y = y;
        }

        public int getX()
        {
                return x;
        }

        public int getY()
        {
                return y;
        }

        public Coord toCoord()
        {
                return new Coord(Coordination.MIN_LAT + Coordination.SIZE_GRID * y, Coordination.MAX_LON - Coordination.SIZE_GRID * x);
        }
}
