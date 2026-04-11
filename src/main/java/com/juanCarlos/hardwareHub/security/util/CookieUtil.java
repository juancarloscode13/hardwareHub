package com.juanCarlos.hardwareHub.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/**
 * Utilidad para construir cookies HttpOnly de autenticación (access_token y refresh_token).
 *
 * @author Juan Carlos
 */
@Component
public class CookieUtil {

    @Value("${app.cookie.secure:false}")
    private boolean secure;

    @Value("${app.cookie.same-site:Strict}")
    private String sameSite;

    /** Cookie de access token — path raíz, se envía en toda petición. */
    public ResponseCookie buildAccessTokenCookie(String token, long maxAgeSeconds) {
        return ResponseCookie.from("access_token", token)
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }

    /**
     * Construye la cookie HttpOnly para el refresh token.
     * Path restringido a /auth/refresh para limitar su exposición.
     */
    public ResponseCookie buildRefreshTokenCookie(String token, long maxAgeSeconds) {
        return ResponseCookie.from("refresh_token", token)
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path("/auth/refresh")
                .maxAge(maxAgeSeconds)
                .build();
    }

    /**
     * Construye una cookie con Max-Age=0 para obligar al navegador a borrarla.
     * Se usa para eliminar cookies de access/refresh al hacer logout.
     */
    public ResponseCookie buildDeleteCookie(String name, String path) {
        return ResponseCookie.from(name, "")
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path(path)
                .maxAge(0)
                .build();
    }
}

