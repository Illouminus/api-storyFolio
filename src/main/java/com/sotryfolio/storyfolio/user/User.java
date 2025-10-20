package com.sotryfolio.storyfolio.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long githubId;

    private String email;
    private String name;
    private String avatar;

    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
