package com.example.responsive_kiosk.manage.controller;

import com.example.responsive_kiosk.order.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class ManageController {

    private final MenuService menuService;

    @GetMapping("/manage/product")
    public ModelAndView productManageForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("menus", menuService.getAll());
        mav.setViewName("product_manage_form");

        return mav;
    }
}
