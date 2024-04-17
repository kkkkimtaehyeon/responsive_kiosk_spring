package com.example.responsive_kiosk.order.controller;

import com.example.responsive_kiosk.order.dto.MenuResponseDto;
import com.example.responsive_kiosk.order.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.order.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.order.service.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuApiController {
    private final MenuService menuService;

    @PostMapping("/api/menus")
    public ResponseEntity<Long> saveMenu(MenuSaveRequestDto requestDto) {
        return menuService.save(requestDto);
    }

    @GetMapping("/api/menus")
    public List<MenuResponseDto> getAllMenus() {
        return menuService.getAll();
    }

    @GetMapping("/api/menus/{id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable("id") Long id) {
        return menuService.get(id);
    }

    @PutMapping("/api/menus/{id}")
    public ResponseEntity<Long> updateMenu(@PathVariable("id") Long id, MenuUpdateRequestDto requestDto) {
        return menuService.update(id, requestDto);
    }

    @DeleteMapping("/api/menus/{id}")
    public void deleteMenu(@PathVariable("id") Long id) {
        menuService.delete(id);
    }

}
