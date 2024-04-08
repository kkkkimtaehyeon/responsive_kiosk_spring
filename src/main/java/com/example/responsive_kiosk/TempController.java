package com.example.responsive_kiosk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/")
    public String main() {
        return "index";
    }
}
