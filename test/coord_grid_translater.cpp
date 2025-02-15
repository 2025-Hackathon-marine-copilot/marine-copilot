#include <cmath>
#include <cstddef>
#include <iostream>
#include <memory>
#include <utility>
#include <vector>

const double min_lat = 35.068818;
const double max_lon = 129.117633;
const double size_grid = 0.002423;

const std::size_t size_x = 24;
const std::size_t size_y = 16;

/**
 * @brief x-y 좌표계를 위도-경도 좌표계로 변환하는 함수
 */
static std::shared_ptr<std::pair<double, double>> grid_to_coord(const std::pair<std::size_t, std::size_t>& grid);

/**
 * @brief 위도-경도 좌표계를 x-y 좌표계로 변환하는 함수
 */
static std::shared_ptr<std::pair<std::size_t, std::size_t>> coord_to_grid(const std::pair<double, double>& grid);

int main(void)
{
        // std::cout << "Please Input Coordination(latitude, longitude): " << std::flush;

        // double lat = 0;
        // double lon = 0;

        // std::cin >> lat >> lon;

        // {
        //         auto grid = coord_to_grid(std::make_pair(lat, lon));

        //         std::cout << "Result: " << grid->first << ", " << grid->second << std::endl;
        // }

        // std::cout << "Please Input Grid(x, y): " << std::flush;

        // double x = 0;
        // double y = 0;

        // std::cin >> x >> y;

        // {
        //         auto coord = grid_to_coord(std::make_pair(x, y));

        //         std::cout << "Result: " << coord->second << ", " << coord->first << std::endl;
        // }

        // std::vector<std::pair<double, double>> coords = { 
        //         std::make_pair(35.091225, 129.117631),
        //         std::make_pair(35.091014, 129.099521), 
        //         std::make_pair(35.093505, 129.094758), 
        //         std::make_pair(35.103725, 129.094028), 
        //         std::make_pair(35.103407, 129.074845), 
        //         std::make_pair(35.108181, 129.067292), 
        //         std::make_pair(35.100527, 129.059481), 
        //         std::make_pair(35.095471, 129.070124),
        //         std::make_pair(35.085939, 129.083382),
        //         std::make_pair(35.080731, 129.096392),
        //         std::make_pair(35.068818, 129.095082),
        //         std::make_pair(35.068818, 129.117631) };

        // std::cout << "[";

        // for(auto c: coords) {
        //         auto grid = coord_to_grid(c);
        //         std::cout << "(" << grid->first << ", " << grid->second << "), ";
        // }

        // std::cout << "]" << std::endl;

        return 0;
}

static std::shared_ptr<std::pair<double, double>> grid_to_coord(const std::pair<std::size_t, std::size_t>& grid)
{
        return std::make_shared<std::pair<double, double>>(std::make_pair(max_lon - size_grid * grid.first, min_lat + size_grid * grid.second));
}

static std::shared_ptr<std::pair<std::size_t, std::size_t>> coord_to_grid(const std::pair<double, double>& coord)
{
        double lon_gap = (coord.first - min_lat) / size_grid;
        double lat_gap = (max_lon - coord.second) / size_grid;

        return std::make_shared<std::pair<std::size_t, std::size_t>>(std::make_pair(static_cast<std::size_t>(std::round(lat_gap)), static_cast<std::size_t>(std::round(lon_gap))));
}
