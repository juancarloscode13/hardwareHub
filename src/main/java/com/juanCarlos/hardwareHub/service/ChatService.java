package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.response.ConversationResponseDto;
import com.juanCarlos.hardwareHub.dto.response.MessageResponseDto;
import com.juanCarlos.hardwareHub.entity.ConversationEntity;
import com.juanCarlos.hardwareHub.entity.MessageEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Servicio correspondiente a la entidad ConversationEntity (con mensajes relacionados)
 *
 * @see ConversationEntity
 * @see MessageEntity
 * @author Juan Carlos
 */
public interface ChatService {

    /**
     * Obtiene o crea una conversación entre dos usuarios. Idempotente.
     *
     * @param currentUserId ID del usuario autenticado
     * @param targetUserId  ID del otro usuario
     * @return Resumen de la conversación
     */
    ConversationResponseDto getOrCreateConversation(Long currentUserId, Long targetUserId);

    /**
     * Envía un mensaje dentro de una conversación.
     *
     * @param senderEmail    email del usuario que envía (del Principal)
     * @param conversationId ID de la conversación
     * @param content        contenido del mensaje
     * @return DTO del mensaje persistido
     */
    MessageResponseDto sendMessage(String senderEmail, Long conversationId, String content);

    /**
     * Historial paginado de mensajes de una conversación.
     *
     * @param conversationId ID de la conversación
     * @param page           número de página
     * @param size           tamaño de página
     * @return página de mensajes (más recientes primero)
     */
    Page<MessageResponseDto> getHistory(Long conversationId, int page, int size);

    /**
     * Marca como leídos todos los mensajes que el usuario NO envió en esa conversación.
     *
     * @param conversationId ID de la conversación
     * @param readerEmail    email del usuario que lee
     * @return
     */
    List<Long> markAsRead(Long conversationId, String readerEmail);

    /**
     * Lista todas las conversaciones del usuario con resumen (último mensaje + no leídos).
     *
     * @param userId ID del usuario
     * @return lista de resúmenes de conversación
     */
    List<ConversationResponseDto> getConversationsForUser(Long userId);

    /**
     * Obtiene el email del receptor de un mensaje en una conversación.
     *
     * @param conversationId ID de la conversación
     * @param senderEmail    email del emisor
     * @return email del receptor
     */
    String getRecipientEmail(Long conversationId, String senderEmail);
}

