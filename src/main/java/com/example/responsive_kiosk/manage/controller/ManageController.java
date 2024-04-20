package com.example.responsive_kiosk.manage.controller;

import com.example.responsive_kiosk.product.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class ManageController {

    private final MenuService menuService;

    @GetMapping("/manage")
    public ModelAndView manageMainForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manage_main_form");
        return mav;
    }

    @GetMapping("/manage/product")
    public ModelAndView productManageForm(@RequestParam(required = false) String keyword) {
        ModelAndView mav = new ModelAndView();
        if (keyword != null) {
            mav.addObject("menus", menuService.getAllByKeyword(keyword));
        }
        mav.addObject("menus", menuService.getAll());
        mav.setViewName("product_manage_form");

        return mav;
    }

    @GetMapping("/manage/product/create")
    public ModelAndView productCreateForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product_create_form");
        return mav;
    }

    @GetMapping("/manage/product/edit/{id}")
    public ModelAndView productEditForm(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product_edit_form");
        mav.addObject("menu", menuService.get(id));
        return mav;
    }
}
