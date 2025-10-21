package com.sotryfolio.storyfolio.api;

import com.sotryfolio.storyfolio.service.RepoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repos")
public class RepoController {
    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }
}
