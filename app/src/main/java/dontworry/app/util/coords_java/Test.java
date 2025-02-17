package dontworry.app.util.coords_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test {
        private static InputStreamReader isr = new InputStreamReader(System.in);
        private static BufferedReader br = new BufferedReader(isr);
        
        private static OutputStreamWriter osw = new OutputStreamWriter(System.out);
        private static BufferedWriter bw = new BufferedWriter(osw);

        public static void main(String[] args) throws IOException
        {
                String input;
                StringTokenizer st;

                // Grid to coord test
                bw.write("Please input grid information (x, y) >> ");
                bw.flush();

                input = br.readLine();
                st = new StringTokenizer(input, "[, ]+");

                Grid g1 = new Grid(Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()));

                Coord c1 = g1.toCoord();

                bw.write("Result >> (" + c1.getLat() + ", " + c1.getLon() + ")\n");
                bw.flush();

                // Coord to grid test
                bw.write("Please input Coordination information (latitude, longitude) >> ");
                bw.flush();

                input = br.readLine();
                st = new StringTokenizer(input, "[, ]+");

                Coord c2 = new Coord(Double.parseDouble(st.nextToken().trim()), Double.parseDouble(st.nextToken().trim()));

                Grid g2 = c2.toGrid();

                bw.write("Result >> (" + g2.getX() + ", " + g2.getY() + ")\n");
                bw.flush();

                // Coordination array(Navigable ocean) to grid array test
                ArrayList<Coord> coords1 = new ArrayList<>();

                int sizeNavigable = Coordination.navigableOcean.length;
                for(int i = 0; i < sizeNavigable; i++)
                        coords1.add(new Coord(Coordination.navigableOcean[i][0], Coordination.navigableOcean[i][1]));

                ArrayList<Grid> grids1 = new ArrayList<>();

                for(Coord c: coords1)
                        grids1.add(c.toGrid());
                

                bw.write("Navigable ocean grid array >> [");

                int sizeGrids = grids1.size();
                for(int i = 0; i < sizeGrids - 1; i++)
                        bw.write("[" + grids1.get(i).getX() + ", " + grids1.get(i).getY() + "], ");

                bw.write("[" + grids1.get(sizeGrids - 1).getX() + ", " + grids1.get(sizeGrids - 1).getY() + "]]");
                bw.newLine();
                bw.flush();

                // Grid array to coordination array test
                bw.write("Please input grid array ([[x, y], [x, y], [x, y], ...]) >> ");
                bw.flush();

                input = br.readLine();
                st = new StringTokenizer(input, "[,\\[\\] ]+");

                ArrayList<Coord> coords2 = new ArrayList<>();
                
                while(st.hasMoreTokens()) {
                        Grid g = new Grid(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                        coords2.add(g.toCoord());
                }

                bw.write("Result >> [");

                int sizeCoords = coords2.size();
                for(int i = 0; i < sizeCoords - 1; i++)
                        bw.write("[" + coords2.get(i).getLat() + ", " + coords2.get(i).getLon() + "], ");

                
                bw.write("[" + coords2.get(sizeCoords - 1).getLat() + ", " + coords2.get(sizeCoords - 1).getLon() + "]]\n");
                bw.flush();

                br.close();
                isr.close();

                bw.close();
                osw.close();
        }
}
