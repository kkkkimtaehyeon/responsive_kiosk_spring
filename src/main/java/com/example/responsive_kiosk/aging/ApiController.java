package com.example.responsive_kiosk.aging;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ApiController {


    //받아온 나이 db에 저장 후 주문 종료 시 폐기 or 세션이나 토큰 발급 후 주문 종료시 폐기
    @PostMapping("/api/face-recognition")
    public ResponseEntity<String> send(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", multipartFile.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // HTTP 요청 엔티티 생성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String url = "http://127.0.0.1:8000/api/face-recognition";

        // REST API 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        responseEntity.getBody();
        return responseEntity;
    }
}
