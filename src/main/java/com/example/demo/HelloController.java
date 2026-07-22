package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "凡是戴鑫森打的单子，我们都坚决跟随，凡是戴鑫森的指示，我们都始终不渝地遵循";
    }
}