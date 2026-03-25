package com.juanCarlos.hardwareHub.security.auth;

import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.RefreshTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import com.juanCarlos.hardwareHub.security.auth.dto.LoginRequestDto;
import com.juanCarlos.hardwareHub.security.auth.dto.LoginResponseDto;
import com.juanCarlos.hardwareHub.security.auth.dto.RegisterRequestDto;
import com.juanCarlos.hardwareHub.security.services.JwtService;
import com.juanCarlos.hardwareHub.security.services.RefreshTokenService;
import com.juanCarlos.hardwareHub.security.util.CookieUtil;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;
    private final RefreshTokenService refreshTokenService;
    private final CookieUtil cookieUtil;
    private final UserDetailsService userDetailsService;

    @Value("${app.jwt.access-expiration:900}")
    private long accessTokenExpiration;

    @Value("${app.jwt.refresh-expiration:604800}")
    private long refreshTokenExpiration;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        UsuarioResponseDto response = usuarioService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest,
                                                   HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 1. Access token (JWT, 15 min)
        String accessToken = jwtService.generateAccessToken(userDetails);

        // 2. Refresh token (UUID opaco, 7 días)
        UsuarioEntity usuario = usuarioService.getByEmail(userDetails.getUsername());
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(usuario);

        // 3. Cookies HttpOnly
        addTokenCookies(response, accessToken, refreshToken.getToken());

        // 4. Body sin datos sensibles
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(UsuarioRol.ROL_USUARIO.getDesc());

        return ResponseEntity.ok(new LoginResponseDto(userDetails.getUsername(), role));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Boolean>> refresh(HttpServletRequest request,
                                                         HttpServletResponse response) {
        String refreshTokenValue = extractCookieValue(request, "refresh_token");
        if (refreshTokenValue == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Validar refresh token actual
        RefreshTokenEntity refreshToken = refreshTokenService.validateRefreshToken(refreshTokenValue);
        UsuarioEntity usuario = refreshToken.getUsuario();
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());

        // Nuevo access token
        String newAccessToken = jwtService.generateAccessToken(userDetails);

        // Rotación: revocar antiguo, crear nuevo
        refreshTokenService.revokeToken(refreshTokenValue);
        RefreshTokenEntity newRefreshToken = refreshTokenService.createRefreshToken(usuario);

        // Cookies actualizadas
        addTokenCookies(response, newAccessToken, newRefreshToken.getToken());

        return ResponseEntity.ok(Map.of("refreshed", true));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // Revocar refresh token si existe
        String refreshTokenValue = extractCookieValue(request, "refresh_token");
        if (refreshTokenValue != null) {
            refreshTokenService.revokeToken(refreshTokenValue);
        }

        // Borrar cookies
        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieUtil.buildDeleteCookie("access_token", "/").toString());
        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieUtil.buildDeleteCookie("refresh_token", "/auth/refresh").toString());

        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDto> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("No hay usuario autenticado");
        }

        String email = authentication.getName();
        UsuarioEntity usuario = usuarioService.getByEmail(email);
        UsuarioResponseDto usuarioDto = usuarioMapper.toResponseDto(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
    }

    // ---- Métodos auxiliares privados ----

    private void addTokenCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        ResponseCookie accessCookie = cookieUtil.buildAccessTokenCookie(accessToken, accessTokenExpiration);
        ResponseCookie refreshCookie = cookieUtil.buildRefreshTokenCookie(refreshToken, refreshTokenExpiration);
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    private String extractCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
