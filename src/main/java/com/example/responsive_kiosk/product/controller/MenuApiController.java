package com.example.responsive_kiosk.product.controller;

import com.example.responsive_kiosk.product.dto.MenuResponseDto;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.product.service.MenuService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /** 메뉴 생성 */
    /*@PostMapping("/api/menus")
    public ResponseEntity<Long> saveMenu(MenuSaveRequestDto requestDto) {
        return menuService.save(requestDto);
    }*/

    @PostMapping("/api/menus")
    public void saveMenu(MenuSaveRequestDto requestDto, HttpServletResponse response) throws IOException {
        menuService.save(requestDto);
        response.sendRedirect("/manage/product");
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
    public void updateMenu(@PathVariable("id") Long id, MenuUpdateRequestDto requestDto, HttpServletResponse response) throws IOException {
        menuService.update(id, requestDto);
        response.sendRedirect("/manage/product");
    }

    @DeleteMapping("/api/menus/{id}")
    public void deleteMenu(@PathVariable("id") Long id) {
        menuService.delete(id);
    }

}
