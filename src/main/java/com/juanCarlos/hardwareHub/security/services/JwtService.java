package com.juanCarlos.hardwareHub.security.services;

import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Servicio encargado de generar y validar tokens JWT de acceso.
 * - Genera access tokens con claims básicos (email, role, issuer, timestamps).
 * - Valida y extrae datos (subject/expiración) de tokens existentes.
 *
 * @author Juan Carlos
 */
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${app.jwt.access-expiration:900}")
    private long accessTokenExpiration;

    public String generateAccessToken(UserDetails userDetails){
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(accessTokenExpiration);

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(UsuarioRol.ROL_USUARIO.getDesc());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("webforms-app")
                .issuedAt(now)
                .expiresAt(expiry)
                .subject(userDetails.getUsername())
                .claim("role", role)
                .claim("email", userDetails.getUsername())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Valida sintáctica y criptográficamente un JWT usando el `JwtDecoder`.
     * Devuelve true si el token se puede decodificar correctamente.
     */
    public boolean isTokenValid(String token){
        try{
            jwtDecoder.decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae el subject (username/email) del token decodificado.
     */
    public String getUsernameFromToken(String token){
        return jwtDecoder.decode(token).getSubject();
    }

    /**
     * Devuelve la fecha de expiración del token como cadena ISO, o null si no existe.
     */
    public String getExpirationFromToken(String token){
        Instant expiresAt = jwtDecoder.decode(token).getExpiresAt();
        return expiresAt != null ? expiresAt.toString() : null;
    }
}
