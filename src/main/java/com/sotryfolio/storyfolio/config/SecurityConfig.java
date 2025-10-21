package com.sotryfolio.storyfolio.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> githubOAuth2UserService;

    public SecurityConfig(OAuth2UserService<OAuth2UserRequest, OAuth2User> githubOAuth2UserService) {
        this.githubOAuth2UserService = githubOAuth2UserService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**",
                                "/api/public/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth.userInfoEndpoint(ui -> ui.userService(githubOAuth2UserService)))
                .logout(lo -> lo.logoutSuccessUrl("/"));
            return http.build();
    }
}
