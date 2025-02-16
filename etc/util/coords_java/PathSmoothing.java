/*
 * Apache commons math 필요
 * 
 * Gradle
 * 
 * // https://mvnrepository.com/artifact/org.apache.commons/commons-math3
 * implementation group: 'org.apache.commons', name: 'commons-math3', version: '3.6.1'
 */

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.ArrayList;
import java.util.List;

public class PathSmoothing {

    public static void main(String[] args) {
        // 테스트 경로
        List<Grid> originalPath = List.of(
            new Grid(0, 9), new Grid(7, 9), new Grid(9, 10), new Grid(10, 14),
            new Grid(18, 14), new Grid(21, 16), new Grid(24, 13), new Grid(20, 11),
            new Grid(14, 7), new Grid(9, 5), new Grid(9, 0), new Grid(0, 0)
        );

        // 보간된 좌표 생성
        List<Grid> smoothPath = interpolatePath(originalPath, originalPath.size() * 5);

        // 위도-경도로 변환
        ArrayList<Coord> coordPath = Coords.gridArrayToCoordArray(new ArrayList<>(smoothPath));

        // 결과 출력
        for (Coord coord : coordPath) {
            System.out.println(coord.lat + ", " + coord.lon);
        }
    }

    /**
     * @brief 주어진 경로를 스플라인 보간하여 부드러운 곡선으로 변환하는 함수
     */
    public static List<Grid> interpolatePath(List<Grid> path, int numPoints) {
        int size = path.size();
        double[] x = new double[size];
        double[] y = new double[size];

        for (int i = 0; i < size; i++) {
            x[i] = path.get(i).x;
            y[i] = path.get(i).y;
        }

        // 스플라인 보간 수행
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction splineX = interpolator.interpolate(range(0, size), x);
        PolynomialSplineFunction splineY = interpolator.interpolate(range(0, size), y);

        // 보간된 경로 생성
        List<Grid> smoothPath = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            double t = ((double) i / (numPoints - 1)) * (size - 1);
            smoothPath.add(new Grid((int) Math.round(splineX.value(t)), (int) Math.round(splineY.value(t))));
        }

        return smoothPath;
    }

    /**
     * @brief 0부터 n-1까지 균등한 간격의 배열 생성
     */
    private static double[] range(int start, int end) {
        double[] arr = new double[end - start];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }
}
