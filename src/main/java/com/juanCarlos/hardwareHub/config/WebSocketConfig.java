package com.juanCarlos.hardwareHub.config;

import com.juanCarlos.hardwareHub.security.AuthHandshakeHandler;
import com.juanCarlos.hardwareHub.security.JwtHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuración de WebSocket + STOMP para el sistema de mensajería directa.
 *
 * <ul>
 *     <li>Endpoint de conexión: {@code /ws} (con SockJS para fallback)</li>
 *     <li>Broker en memoria con destinos {@code /queue} y {@code /topic}</li>
 *     <li>Prefijo de aplicación: {@code /app}</li>
 *     <li>Prefijo de usuario: {@code /user} (para {@code convertAndSendToUser})</li>
 * </ul>
 *
 * @author Juan Carlos
 */
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;
    private final AuthHandshakeHandler authHandshakeHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(authHandshakeHandler)
                .addInterceptors(jwtHandshakeInterceptor)
                .setAllowedOrigins("http://localhost:5173")
                .withSockJS();
    }
}

