package com.sotryfolio.storyfolio.service;

import com.sotryfolio.storyfolio.repo.Repo;
import com.sotryfolio.storyfolio.repo.RepoRepository;
import com.sotryfolio.storyfolio.user.User;
import com.sotryfolio.storyfolio.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepoService {

    private final RepoRepository repoRepository;
    private final UserRepository userRepository;

    public RepoService(RepoRepository repoRepository, UserRepository userRepository) {
        this.repoRepository = repoRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Repo> listUserRepo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return repoRepository.findByUser(user);
    }
}
