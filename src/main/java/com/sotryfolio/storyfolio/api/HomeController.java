package com.sotryfolio.storyfolio.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String root() {
        // Serve the static login page located in src/main/resources/static/login.html
        return "forward:/login.html";
    }

    @GetMapping("/login")
    public String login() {
        // Expose a custom login endpoint expected by Spring Security
        return "forward:/login.html";
    }
}
