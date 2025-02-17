package dontworry.app.util.coords_java;

public class Coord
{
        private double lat, lon;

        public Coord(double lat, double lon)
        {
                this.lat = lat;
                this.lon = lon;
        }

        public double getLat()
        {
                return lat;
        }

        public double getLon()
        {
                return lon;
        }

        public Grid toGrid()
        {
                double lat_gap = (lat - Coordination.MIN_LAT) / Coordination.SIZE_GRID;
                double lon_gap = (Coordination.MAX_LON - lon) / Coordination.SIZE_GRID;

                return new Grid((int) Math.round(lon_gap), (int) Math.round(lat_gap));
        }
}
