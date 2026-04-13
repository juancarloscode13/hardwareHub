package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Implementación del servicio de envío de correos electrónicos.
 * Utiliza JavaMailSender para enviar emails y Thymeleaf para renderizar plantillas HTML.
 *
 * @see EmailService
 * @author Juan Carlos
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /** {@inheritDoc} */
    @Override
    public void sendPasswordResetEmail(String to, String resetLink) {
        Context context = new Context();
        context.setVariable("resetLink", resetLink);

        String htmlContent = templateEngine.process("password-reset-email", context);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("HardwareHub — Restablecer contraseña");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Email de recuperación de contraseña enviado a: {}", to);
        } catch (MessagingException | MailException e) {
            log.error("Error al enviar email de recuperación de contraseña a: {}", to, e);
            throw new RuntimeException("No se pudo enviar el email de recuperación", e);
        }
    }
}

