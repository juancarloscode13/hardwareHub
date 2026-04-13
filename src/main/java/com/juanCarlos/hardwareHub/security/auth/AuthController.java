package com.juanCarlos.hardwareHub.security.auth;

import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.RefreshTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.security.auth.dto.ForgotPasswordRequestDto;
import com.juanCarlos.hardwareHub.security.auth.dto.LoginRequestDto;
import com.juanCarlos.hardwareHub.security.auth.dto.LoginResponseDto;
import com.juanCarlos.hardwareHub.security.auth.dto.RegisterRequestDto;
import com.juanCarlos.hardwareHub.security.auth.dto.ResetPasswordRequestDto;
import com.juanCarlos.hardwareHub.security.services.JwtService;
import com.juanCarlos.hardwareHub.security.services.RefreshTokenService;
import com.juanCarlos.hardwareHub.security.util.CookieUtil;
import com.juanCarlos.hardwareHub.service.PasswordResetService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST para operaciones de autenticación: registro, login,
 * refresh de tokens, logout y obtener el usuario actual (`/auth/me`).
 * Gestiona cookies HttpOnly para access/refresh tokens y la rotación de
 * refresh tokens persistidos.
 *
 * @author Juan Carlos
 */
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
    private final PasswordResetService passwordResetService;

    @Value("${app.jwt.access-expiration:900}")
    private long accessTokenExpiration;

    @Value("${app.jwt.refresh-expiration:604800}")
    private long refreshTokenExpiration;

    /**
     * Registra un nuevo usuario usando los datos del DTO.
     * Valida el DTO con @Valid y delega la creación al servicio `UsuarioService`.
     * Devuelve 201 CREATED con el DTO de respuesta del usuario (sin datos sensibles).
     */
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        UsuarioResponseDto response = usuarioService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Autentica las credenciales del usuario.
     * - Realiza la autenticación con `AuthenticationManager`.
     * - Genera un access token (JWT) y un refresh token persistido.
     * - Escribe ambos tokens como cookies HttpOnly en la respuesta.
     * - Devuelve en el body el username y el rol del usuario autenticado.
     */
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
        String role = usuario.getRol().name();

        return ResponseEntity.ok(new LoginResponseDto(userDetails.getUsername(), role));
    }

    /**
     * Endpoint para rotación de refresh token.
     * - Lee el refresh token de la cookie `refresh_token`.
     * - Valida el token persistido y genera un nuevo access token.
     * - Revoca el refresh token antiguo y crea uno nuevo (rotación).
     * - Devuelve cookies actualizadas con los nuevos tokens.
     */
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

    /**
     * Logout: revoca el refresh token (si existe) y borra las cookies de acceso
     * y refresh del cliente. Limpia el contexto de seguridad.
     */
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

    /**
     * Devuelve los datos del usuario actualmente autenticado (sin datos sensibles).
     * Lanza BadCredentialsException si no hay usuario autenticado en el contexto.
     */
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

    //Métodos de recuperación de contraseña

    /**
     * Solicita el envío de un email de recuperación de contraseña.
     * Siempre responde 200 OK para evitar enumeración de usuarios
     * (no revela si el email existe o no en el sistema).
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto request) {
        passwordResetService.requestPasswordReset(request.getEmail());
        return ResponseEntity.ok(Map.of("message", "Si el email está registrado, recibirás un correo con instrucciones."));
    }

    /**
     * Restablece la contraseña del usuario usando el token de recuperación.
     * Valida el token, actualiza la contraseña y revoca las sesiones activas.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@Valid @RequestBody ResetPasswordRequestDto request) {
        passwordResetService.resetPassword(request.getToken(), request.getNuevaContrasena());
        return ResponseEntity.ok(Map.of("message", "Contraseña restablecida correctamente."));
    }

    //Métodos auxiliares privados

    /**
     * Helper privado: construye y añade a la respuesta las cookies HttpOnly
     * para access token y refresh token.
     */
    private void addTokenCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        ResponseCookie accessCookie = cookieUtil.buildAccessTokenCookie(accessToken, accessTokenExpiration);
        ResponseCookie refreshCookie = cookieUtil.buildRefreshTokenCookie(refreshToken, refreshTokenExpiration);
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    /**
     * Helper privado: extrae el valor de una cookie por nombre de la request.
     * Devuelve null si no existe.
     */
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
