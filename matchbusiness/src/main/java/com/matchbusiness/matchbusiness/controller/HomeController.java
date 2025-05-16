package com.matchbusiness.matchbusiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Página pública ("/")
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    // Página após login ("/dashboard")
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // templates/dashboard.html
    }
}
