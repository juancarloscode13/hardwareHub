package com.juanCarlos.hardwareHub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de swagger. Permite probar los distintos endpoints de la api con
 * una interfaz grafica (en funcionalidad, es parecido a Postman).
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hardwareHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HardwareHub API")
                        .description("REST API documentation for HardwareHub — gestión de componentes de hardware, montajes y publicaciones")
                        .version("1.0"))
                .components(new Components()
                        .addSecuritySchemes("cookieAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.COOKIE)
                                .name("access_token")))
                        .addSecurityItem(new  SecurityRequirement().addList("cookieAuth"));
    }
}

