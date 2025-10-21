package com.sotryfolio.storyfolio.repo;

import com.sotryfolio.storyfolio.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "repos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Long githubRepoId;
    private String name;
    private String fullName;
    private String visibility;
    private String defaultBranch;
    private Integer stars;
    private Integer forks;
    private String primaryLang;

    private OffsetDateTime lastScannedAt;
}
