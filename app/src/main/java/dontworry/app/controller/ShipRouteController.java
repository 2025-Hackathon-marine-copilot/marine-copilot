package dontworry.app.controller;

import dontworry.app.util.coords_java.Coord;
import dontworry.app.util.coords_java.Coords;
import dontworry.app.util.coords_java.Grid;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("route")
public class ShipRouteController {
    private final String flaskApiUrl = "http://localhost:9000/predict";

    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> getOptimalShipRoute(@RequestBody Map<String, double[]> requestData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        double[] startCoords = requestData.get("start");
        double[] goalCoords = requestData.get("goal");

        Coord start = new Coord(startCoords[0], startCoords[1]);
        Grid convertedStart = Coords.coordToGrid(start);
        Coord goal = new Coord(goalCoords[0], goalCoords[1]);
        Grid convertedGoal = Coords.coordToGrid(goal);
        requestData.put("start", new double[]{convertedStart.x, convertedStart.y});
        requestData.put("goal", new double[]{convertedGoal.x, convertedGoal.y});

        HttpEntity<Map<String, double[]>> request = new HttpEntity<>(requestData, headers);
        ResponseEntity<Map> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ArrayList<ArrayList<Integer>> arr = (ArrayList<ArrayList<Integer>>) response.getBody().get("path");
            ArrayList<Coord> list = new ArrayList<>();

            for(int i = 0; i < arr.size(); i++){
                Grid grid = new Grid(arr.get(i).get(0), arr.get(i).get(1));
                Coord coord = Coords.gridToCoord(grid);
                list.add(coord);
            }
            response.getBody().put("route", list);
            return ResponseEntity.ok(response.getBody());

        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
}
