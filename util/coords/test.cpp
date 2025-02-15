#include "coords.h"

int main(void)
{
        // Coordination to grid
        // std::cout << "Please input coordination(latitude longitude) << " << std::flush;

        // double lat = 0;
        // double lon = 0;

        // std::cin >> lat >> lon;

        // {
        //         auto grid = coord_to_grid(coords::Coord{lat, lon});

        //         std::cout << "Result: (" << grid->x << ", " << grid->y << ")" << std::endl;
        // }

        // Grid to coordination
        // std::cout << "Please input grid(x y) << " << std::flush;

        // double x = 0;
        // double y = 0;

        // std::cin >> x >> y;

        // {
        //         auto coord = grid_to_coord(coords::Grid{x, y});

        //         std::cout << "Result: (" << coord->lat << ", " << coord->lon << ")" << std::endl;
        // }

        // Navigable waters test
        std::vector<coords::Coord> coords = { 
                coords::Coord{35.091225, 129.117631},
                coords::Coord{35.091014, 129.099521}, 
                coords::Coord{35.093505, 129.094758}, 
                coords::Coord{35.103725, 129.094028}, 
                coords::Coord{35.103407, 129.074845}, 
                coords::Coord{35.108181, 129.067292}, 
                coords::Coord{35.100527, 129.059481}, 
                coords::Coord{35.095471, 129.070124},
                coords::Coord{35.085939, 129.083382},
                coords::Coord{35.080731, 129.096392},
                coords::Coord{35.068818, 129.095082},
                coords::Coord{35.068818, 129.117631} };

        std::vector<coords::Grid> grids;
        grids.reserve(coords.size());

        {
                for(auto c: coords)
                        grids.push_back(*(coord_to_grid(c)));
        }
        
        std::cout << "[";

        std::size_t size_grids = grids.size();
        for(int i = 0; i < size_grids - 1; i++)
                std::cout << "(" << grids[i].x << ", " << grids[i].y << "), ";

        std::cout << "(" << grids[size_grids - 1].x << ", " << grids[size_grids - 1].y << ")]" << std::endl;

        return 0;
}
