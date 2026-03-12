package com.juanCarlos.hardwareHub.security.services;

import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final long jwtExpiration;
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public String generateToken(UserDetails userDetails){
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(jwtExpiration);

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
                .claim("email", usuarioService.getByEmail(userDetails.getUsername()))
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
