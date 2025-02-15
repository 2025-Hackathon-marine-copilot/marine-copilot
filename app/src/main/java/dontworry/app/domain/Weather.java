package dontworry.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Weather {
    private String tm; // 관측 시각
    private String wh; // 유의 파고
    private String wd; // 풍향
    private String ws; // 풍속
    private String wsGst; // GUST 풍속
    private String tw; // 해수면 온도
    private String ta; // 기온
    private String pa; // 해면 기압
}
