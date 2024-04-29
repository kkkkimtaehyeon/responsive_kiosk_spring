package com.example.responsive_kiosk.toFastApi;

import com.example.responsive_kiosk.config.service.S3UploadService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RequiredArgsConstructor
@RestController
public class ToFastApiController {
    private final S3UploadService s3UploadService;

   /* *//** React에서 캡처된 이미지 받아오는 테스트, CORS 설정 필요*//*
    @PostMapping("/face-capture")
    public void test(@RequestParam("capturedImg") MultipartFile file) throws IOException {
        s3UploadService.saveFile(file);
    }*/

}
