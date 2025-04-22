package com.smart.health.care.management.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SwaggerController {
    @GetMapping
    public String hello() {
        return "Hello Swagger!";
    }
}
