package com.itu.coworking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityHandler {
@GetMapping("/access-denied")
public String accessDenied(){
    return "accessDenied";
}
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}
