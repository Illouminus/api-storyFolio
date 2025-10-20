package com.sotryfolio.storyfolio.auth;

import com.sotryfolio.storyfolio.user.User;
import com.sotryfolio.storyfolio.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attrs = oAuth2User.getAttributes();
        // GitHub user payload содержит "id", "name", "avatar_url", "email" (email может быть null)
        Long githubId = ((Number) attrs.get("id")).longValue();
        String name = (String) attrs.getOrDefault("name", "GitHub User");
        String avatar = (String) attrs.get("avatar_url");
        String email = (String) attrs.get("email");

        // upsert
        User user = userRepository.findByGithubId(githubId)
                .map(u -> {
                    u.setName(name);
                    u.setAvatar(avatar);
                    if (email != null) u.setEmail(email);
                    return u;
                })
                .orElseGet(() -> User.builder()
                        .githubId(githubId)
                        .name(name)
                        .avatar(avatar)
                        .email(email)
                        .build()
                );
        userRepository.save(user);

        // возвращаем principal с "id" как ключевым атрибутом
        return new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("ROLE_USER")),
                attrs,
                "id"
        );
    }
}