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

    public boolean isTokenValid(String token){
        try{
            jwtDecoder.decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        return jwtDecoder.decode(token).getSubject();
    }

    public String getExpirationFromToken(String token){
        Instant expiresAt = jwtDecoder.decode(token).getExpiresAt();
        return expiresAt != null ? expiresAt.toString() : null;
    }
}
