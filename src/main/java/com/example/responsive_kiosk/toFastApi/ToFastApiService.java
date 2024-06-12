package com.example.responsive_kiosk.toFastApi;

import com.example.responsive_kiosk.product.dto.MenuSaveOnGPTRequestDto;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToFastApiService {

    String tempPort = "https://responsivekiosk.store";

    public ResponseEntity<String> registerMenuOnGPT(MenuSaveOnGPTRequestDto requestDto) {

        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate은 기본적으로 formdata 전송
        ResponseEntity<String> registered = restTemplate.postForEntity(tempPort+"/fast/api/menus", requestDto, String.class);
        return registered;
    }

    public ResponseEntity<String> deleteMenuOnGPT(Long menuId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = tempPort+"/fast/api/menus/" + menuId;
        restTemplate.delete(url);

        return ResponseEntity.ok("menu id: "+ menuId+ "successfully deleted");
    }


}
