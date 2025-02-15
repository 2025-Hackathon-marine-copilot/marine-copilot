package dontworry.app.service;

import dontworry.app.domain.Weather;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.*;
import java.io.*;

@Service
public class PortWeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    public Weather getWeatherInfo() {
        String apiUrl = "https://apihub.kma.go.kr/api/typ01/url/sea_obs.php?tm=&stn=516090&help=0&authKey=" + apiKey;
        String extractedData = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            int lineCount = 0;

            while ((line = in.readLine()) != null) {
                if (lineCount == 3) { // 두 번째 줄
                    extractedData = line.trim();
                    break;
                }
                lineCount++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error..");
            return null;
        }

        String[] arr = extractedData.split(",");
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i].trim();
        }

        Weather weather = new Weather(arr[1], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12]);

        return weather;
    }
}
