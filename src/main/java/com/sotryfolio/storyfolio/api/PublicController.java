package com.sotryfolio.storyfolio.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping
    public String ping() {
        return "pong";
    }
}
