//package dontworry.app.api;
//
//import java.net.*;
//import java.io.*;
//
//public class PortWeatherInfo {
//    public static void main(String[] args) throws Exception {
//
//        // API URL을 만듭니다.
//        URL url = new URL("https://apihub.kma.go.kr/api/typ01/url/sea_obs.php?tm=&stn=516090&help=0&authKey=a8j-o_bXStqI_qP21yraYg");
//        // HttpURLConnection 객체를 만들어 API를 호출합니다.
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        // 요청 방식을 GET으로 설정합니다.
//        con.setRequestMethod("GET");
//        // 요청 헤더를 설정합니다. 여기서는 Content-Type을 application/json으로 설정합니다.
//        con.setRequestProperty("Content-Type", "application/json");
//
//        // API의 응답을 읽기 위한 BufferedReader를 생성합니다.
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        // 응답을 한 줄씩 읽어들이면서 StringBuffer에 추가합니다.
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        // BufferedReader를 닫습니다.
//        in.close();
//
//        // 응답을 출력합니다.
//        System.out.println(response.toString());
//
//    }
//
//}
