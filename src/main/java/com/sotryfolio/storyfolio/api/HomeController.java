package com.sotryfolio.storyfolio.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class HomeController {
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String root() {
        return "forward:/login.html";
    }
}
