package com.example.responsive_kiosk.product.controller;

import com.example.responsive_kiosk.config.service.S3UploadService;
import com.example.responsive_kiosk.product.dto.MenuResponseDto;
import com.example.responsive_kiosk.product.dto.MenuResponseDto2;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.product.service.MenuService;
import com.example.responsive_kiosk.toFastApi.ToFastApiController;
import com.example.responsive_kiosk.toFastApi.ToFastApiService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class MenuApiController {
    private final MenuService menuService;
    private final S3UploadService s3UploadService;

    @PostMapping("/api/menus")
    public void saveMenu(MenuSaveRequestDto requestDto, HttpServletResponse response) throws IOException {
        menuService.save(requestDto);
        response.sendRedirect("/manage/products");
    }

    /** 메뉴 업로드를 위한 GPT로 메뉴 정보 전송*/
    /*@PostMapping("/api/menu-register")
    public ResponseEntity<String> registerMenu(@RequestBody MenuSaveRequestDto requestDto) {
        return toFastApiService.registerMenuOnGPT(requestDto);
    }*/

    @PostMapping("/api/upload")
    public String upload(@RequestParam("img") MultipartFile file) throws IOException {
        return s3UploadService.saveFile(file);
    }

    /* 전체 메뉴 */
    @GetMapping("/api/menus")
    public List<?> getMenus(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String categoryname,
                               @RequestParam(required = false) List<Long> id) {
        if(keyword != null) {
            return menuService.getAllByKeyword(keyword);
        }
        else if(id != null) {
            return menuService.getMenusByIds(id);
        }
        else if(categoryname != null) {
            return menuService.getMenusByCategory(categoryname);
        }
        return menuService.getAll();
    }

//    /* 다수의 특정 메뉴*/
//    @GetMapping("/api/menus")
//    public List<MenuResponseDto2> getMenus(@RequestParam("id") List<Long> id) {
//
//    }

    /* 특정 메뉴 */
    @GetMapping("/api/menus/{id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable("id") Long id) {
        return menuService.get(id);
    }

    @PutMapping("/api/menus/{id}")
    public void updateMenu(@PathVariable("id") Long id, MenuUpdateRequestDto requestDto, HttpServletResponse response) throws IOException {
        menuService.update(id, requestDto);
        response.sendRedirect("/manage/products");
    }

    @DeleteMapping("/api/menus/{id}")
    public void deleteMenu(@PathVariable("id") Long id) {
        menuService.delete(id);
    }

}
