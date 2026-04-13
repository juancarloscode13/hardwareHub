package com.juanCarlos.hardwareHub.service;

/**
 * Servicio de envío de correos electrónicos de la aplicación.
 *
 * @author Juan Carlos
 */
public interface EmailService {

    /**
     * Envía un email de recuperación de contraseña al destinatario,
     * renderizando la plantilla Thymeleaf con el enlace de reset proporcionado.
     *
     * @param to        dirección de correo del destinatario
     * @param resetLink enlace completo para restablecer la contraseña
     */
    void sendPasswordResetEmail(String to, String resetLink);
}

