package com.example.responsive_kiosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ResponsiveKioskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResponsiveKioskApplication.class, args);
    }

}
