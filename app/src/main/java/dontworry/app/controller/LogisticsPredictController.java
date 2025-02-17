package dontworry.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class LogisticsPredictController {
    // ✅ MacOS Desktop/image 폴더에 저장하도록 경로 설정
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/Desktop/image";

    private static final String FLASK_SERVER_URL = "http://127.0.0.1:9000/predictLogistics";

    @PostMapping
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("image") MultipartFile image) {
        Map<String, Object> response = new HashMap<>();

        try {
            // ✅ 1. Desktop/image 폴더가 없으면 생성
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // ✅ 2. 이미지 저장
            String filePath = saveImage(image);

            // ✅ 3. Flask 서버에 이미지 전송
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> flaskRequest = new HashMap<>();
            flaskRequest.put("image_path", filePath);

            ResponseEntity<Map> flaskResponse = restTemplate.postForEntity(FLASK_SERVER_URL, flaskRequest, Map.class);

            // ✅ 4. Flask 결과 반환
            response.put("status", "success");
            response.put("saved_path", filePath);
            response.put("flask_result", flaskResponse.getBody());
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "이미지 업로드 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + "/" + filename;
        File dest = new File(filePath);
        file.transferTo(dest);
        System.out.println("✅ 저장된 이미지 경로: " + dest.getAbsolutePath()); // 로그 추가
        return filePath;
    }
}
