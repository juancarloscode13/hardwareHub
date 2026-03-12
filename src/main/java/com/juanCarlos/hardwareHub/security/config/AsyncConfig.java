package com.juanCarlos.hardwareHub.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    //Habilita operaciones asíncronas
    //Spring Boot maneja automáticamente el pool de hilos
}
