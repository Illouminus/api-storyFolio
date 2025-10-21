package com.sotryfolio.storyfolio.config;

import com.sotryfolio.storyfolio.auth.GithubOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final GithubOAuth2UserService githubOAuth2UserService;

    public SecurityConfig(GithubOAuth2UserService githubOAuth2UserService) {
        this.githubOAuth2UserService = githubOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error", "/swagger/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login") // опционально, если хочешь свою страницу
                        .userInfoEndpoint(userInfo -> userInfo.userService(githubOAuth2UserService))
                        .defaultSuccessUrl("/swagger", true) // после логина идём на Swagger
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}