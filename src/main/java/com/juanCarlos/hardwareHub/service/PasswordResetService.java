package com.juanCarlos.hardwareHub.service;

/**
 * Servicio de recuperación y restablecimiento de contraseña.
 *
 * @author Juan Carlos
 */
public interface PasswordResetService {

    /**
     * Solicita la recuperación de contraseña para el email dado.
     * Genera un token de reset, lo persiste y envía un email con el enlace.
     * Si el email no existe, no lanza excepción.
     *
     * @param email dirección de correo del usuario
     */
    void requestPasswordReset(String email);

    /**
     * Restablece la contraseña del usuario asociado al token dado.
     * Valida el token, actualiza la contraseña cifrada y revoca todas las sesiones activas.
     *
     * @param token           token UUID de recuperación
     * @param nuevaContrasena nueva contraseña en texto plano
     */
    void resetPassword(String token, String nuevaContrasena);
}

