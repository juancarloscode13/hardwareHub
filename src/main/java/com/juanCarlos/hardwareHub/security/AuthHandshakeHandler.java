package com.juanCarlos.hardwareHub.security;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Handler personalizado que extrae la {@link UsernamePasswordAuthenticationToken}
 * almacenada por {@link JwtHandshakeInterceptor} en los atributos de sesión WS
 * y la establece como el {@code Principal} de la sesión WebSocket.
 *
 * <p>Esto permite que {@code convertAndSendToUser(email, ...)} funcione
 * correctamente, ya que Spring STOMP resuelve el destinatario por
 * {@code Principal.getName()} (que es el email del usuario).</p>
 *
 * @author Juan Carlos
 */
@Component
public class AuthHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        Object auth = attributes.get(JwtHandshakeInterceptor.AUTH_ATTR);
        if (auth instanceof UsernamePasswordAuthenticationToken token) {
            return token;
        }
        return super.determineUser(request, wsHandler, attributes);
    }
}

