#ifndef COORDS_H
#define COORDS_H

#define COORDS_VERSION "1.0.0"

#include <cmath>
#include <cstddef>
#include <iostream>
#include <memory>
#include <utility>
#include <vector>

namespace coords
{
        class Coord
        {
        public:
                Coord(double latitude, double longitude): lat(latitude), lon(longitude) {  }

                double lat, lon;
        };

        class Grid
        {
        public:
                Grid(std::size_t i, std::size_t j): x(i), y(j) {  }

                std::size_t x, y;
        };

        enum Precision
        {
                M, // 1m
                HM, // 100m
                KM, // 1km
                HKM // 100km
        };
}

const double MIN_LAT = 35.068818;
const double MAX_LON = 129.117633;
const double SIZE_GRID = 0.002423;

const std::size_t SIZE_X = 24;
const std::size_t SIZE_Y = 16;

/**
 * @brief 지점 p가 구역 map 내부에 있는지 확인하는 함수
 */
bool inner_map(const coords::Coord& p, const std::vector<coords::Coord>& map, const coords::Precision& precision = coords::Precision::HM);

/**
 * @brief x-y 좌표계를 위도-경도 좌표계로 변환하는 함수
 */
std::shared_ptr<coords::Coord> grid_to_coord(const coords::Grid& grid);

/**
 * @brief 위도-경도 좌표계를 x-y 좌표계로 변환하는 함수
 */
std::shared_ptr<coords::Grid> coord_to_grid(const coords::Coord& grid);
#endif
