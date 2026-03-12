package com.juanCarlos.hardwareHub.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class SecurityConstants {

    @Bean
    public Set<String>allowedEndpoints(){
        return Set.of(
                "/auth/**"
        );
    }
}
