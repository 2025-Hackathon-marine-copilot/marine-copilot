package dontworry.app.myclass;

import java.util.Random;

public class RandomCoordinateGenerator {
    double lat1 = 35.08346392459766, lng1 = 129.094934463501;
    double lat2 = 35.08806429179801, lng2 = 129.1026592254639;

    public double[] getRandomCoordinate() {
        Random random = new Random();
        double t = random.nextDouble(); // 0 ~ 1 사이의 랜덤 값

        // 랜덤한 비율(t)에 따라 두 점 사이의 위치 계산
        double randomLat = (1 - t) * lat1 + t * lat2;
        double randomLng = (1 - t) * lng1 + t * lng2;

        return new double[]{35.06810151572369, 129.11759376525882};
    }
}
