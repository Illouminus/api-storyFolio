package com.sotryfolio.storyfolio.repo;

import com.sotryfolio.storyfolio.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoRepository extends JpaRepository<Repo, Long> {
    List<Repo> findByUser(User user);
}
