package com.example.responsive_kiosk.order.controller;

import com.example.responsive_kiosk.order.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.order.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuApiController {
    private final MenuService menuService;

    @PostMapping("/api/menus")
    public ResponseEntity<Long> saveMenu(MenuSaveRequestDto requestDto) {
        return menuService.save(requestDto);
    }


}
