package dontworry.app.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

        HttpEntity<Map<String, double[]>> request = new HttpEntity<>(requestData, headers);
        ResponseEntity<Map> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());

        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }


}
