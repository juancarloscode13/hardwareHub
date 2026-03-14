package com.juanCarlos.hardwareHub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hardwareHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HardwareHub API")
                        .description("REST API documentation for HardwareHub — gestión de componentes de hardware, montajes y publicaciones")
                        .version("1.0"));
    }
}

