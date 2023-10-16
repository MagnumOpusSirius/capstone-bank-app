package com.project.bankapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("http://localhost:3000")
                .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE");
//                .allowedHeaders("Authorization", "Content-Type")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true);

    }
}
