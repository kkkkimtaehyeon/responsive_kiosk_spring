package com.example.responsive_kiosk.config.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveFile(MultipartFile file) throws IOException {
        try {
            String originalFilename = UUID.randomUUID() + "_" +file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(bucket, originalFilename, file.getInputStream(), metadata);
            return amazonS3.getUrl(bucket, originalFilename).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "image  upload failed";
        }
    }

    public void deleteFile(String url) {
        String key = extractKeyname(url);

        boolean isExist = amazonS3.doesObjectExist(bucket, key);
        if(isExist) {
            amazonS3.deleteObject(bucket, key);
        }
    }

    public String extractKeyname(String url) {
        return url.split(".2.amazonaws.com/")[1];
    }
}