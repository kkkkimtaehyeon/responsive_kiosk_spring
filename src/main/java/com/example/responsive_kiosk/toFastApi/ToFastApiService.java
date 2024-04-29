package com.example.responsive_kiosk.toFastApi;

import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToFastApiService {

    public ResponseEntity<String> registerMenuOnGPT(MenuSaveRequestDto requestDto) {

        RestTemplate restTemplate = new RestTemplate();

        //RestTemplate은 기본적으로 formdata 전송
        ResponseEntity<String> registered = restTemplate.postForEntity(
                "http://localhost:8000/api/menu-register", requestDto, String.class);

        return registered;
    }


}
