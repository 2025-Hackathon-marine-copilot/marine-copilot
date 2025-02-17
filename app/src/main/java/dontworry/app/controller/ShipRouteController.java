package dontworry.app.controller;

import dontworry.app.util.coords_java.Coord;
import dontworry.app.util.coords_java.Coordination;
import dontworry.app.util.coords_java.Grid;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("route")
public class ShipRouteController {
    private final String flaskApiUrl = "http://localhost:9200/predict";

    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> getOptimalShipRoute(@RequestBody Map<String, Object> requestData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        double[] startCoords = ((List<Double>) requestData.get("start")).stream().mapToDouble(Double::doubleValue).toArray();
        double[] goalCoords = ((List<Double>) requestData.get("goal")).stream().mapToDouble(Double::doubleValue).toArray();

        Coord start = new Coord(startCoords[0], startCoords[1]);
        Grid convertedStart = start.toGrid();
        Coord goal = new Coord(goalCoords[0], goalCoords[1]);
        Grid convertedGoal = goal.toGrid();

        requestData.put("start", new double[]{convertedStart.getX(), convertedStart.getY()});
        requestData.put("goal", new double[]{convertedGoal.getX(), convertedGoal.getY()});

        // 📌 2. `movingShip` 좌표 변환
        List<List<Double>> movingShipList = (List<List<Double>>) requestData.get("movingShip");
        List<double[]> convertedMovingShip = movingShipList.stream()
                .map(coord -> {
                    Coord originalCoord = new Coord(coord.get(0), coord.get(1));
                    Grid convertedCoord = originalCoord.toGrid();
                    return new double[]{convertedCoord.getX(), convertedCoord.getY()};
                })
                .toList();
        requestData.put("movingShip", convertedMovingShip);

        // 📌 3. `anchoredShip` 좌표 변환
        List<List<Double>> anchoredShipList = (List<List<Double>>) requestData.get("anchoredShip");
        List<double[]> convertedAnchoredShip = anchoredShipList.stream()
                .map(coord -> {
                    Coord originalCoord = new Coord(coord.get(0), coord.get(1));
                    Grid convertedCoord = originalCoord.toGrid();
                    return new double[]{convertedCoord.getX(), convertedCoord.getY()};
                })
                .toList();
        requestData.put("anchoredShip", convertedAnchoredShip);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, headers);
        ResponseEntity<Map> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ArrayList<ArrayList<Integer>> arr = (ArrayList<ArrayList<Integer>>) response.getBody().get("path");
            ArrayList<Coord> path = new ArrayList<>();

            for(int i = 0; i < arr.size(); i++){
                Grid grid = new Grid(arr.get(i).get(0), arr.get(i).get(1));
                Coord coord = grid.toCoord();
                path.add(coord);
            }
            response.getBody().put("route", path);
            return ResponseEntity.ok(response.getBody());

        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
}
