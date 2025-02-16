import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Test {
        public static void main(String[] args) throws IOException
        {
                // Navigable waters test
                ArrayList<Coord> coords = new ArrayList<>();
                
                coords.add(new Coord(35.091225, 129.117631));
                coords.add(new Coord(35.091014, 129.099521)); 
                coords.add(new Coord(35.093505, 129.094758)); 
                coords.add(new Coord(35.103725, 129.094028)); 
                coords.add(new Coord(35.103407, 129.074845)); 
                coords.add(new Coord(35.108181, 129.067292)); 
                coords.add(new Coord(35.100527, 129.059481)); 
                coords.add(new Coord(35.095471, 129.070124));
                coords.add(new Coord(35.085939, 129.083382));
                coords.add(new Coord(35.080731, 129.096392));
                coords.add(new Coord(35.068818, 129.095082));
                coords.add(new Coord(35.068818, 129.117631));

                ArrayList<Grid> grids = new ArrayList<>();

                for(Coord c: coords)
                        grids.add(Coords.coordToGrid(c));
                
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

                bw.write("[");

                int sizeGrids = grids.size();
                for(int i = 0; i < sizeGrids - 1; i++)
                        bw.write("(" + grids.get(i).x + ", " + grids.get(i).y + "), ");

                bw.write("(" + grids.get(sizeGrids - 1).x + ", " + grids.get(sizeGrids - 1).y + ")]");
                bw.newLine();

                bw.flush();

                bw.close();
        }
}
