package com.itu.coworking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAdminController {
    @GetMapping("/loginAdmin")
    public String loginAdmin(){
        return "Admin/loginAdmin";
    }
}
