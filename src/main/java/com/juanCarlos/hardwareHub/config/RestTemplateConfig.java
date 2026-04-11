package com.juanCarlos.hardwareHub.config;

import com.juanCarlos.hardwareHub.service.implementation.NoticiaServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase de configuracion que expone un RestTemplate
 * Permite realizar peticiones síncronas a apis externas. Es consumida por {@link NoticiaServiceImplementation}.
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

