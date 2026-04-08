package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio correspondiente a la entidad MessageEntity
 *
 * @see MessageEntity
 * @author Juan Carlos
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    /** Historial paginado de mensajes de una conversación (más recientes primero). */
    Page<MessageEntity> findByConversationIdOrderBySentAtDesc(Long conversationId, Pageable pageable);

    /** Último mensaje de una conversación (para resumen). */
    Optional<MessageEntity> findFirstByConversationIdOrderBySentAtDesc(Long conversationId);

    /** Cuenta mensajes no leídos en una conversación para un usuario (los que NO envió él). */
    @Query("SELECT COUNT(m) FROM MessageEntity m WHERE m.conversation.id = :convId AND m.sender.id <> :userId AND m.isRead = false")
    long countUnreadByConversationAndUser(@Param("convId") Long conversationId, @Param("userId") Long userId);

    /** IDs de mensajes no leídos de una conversación para un usuario (solo mensajes del otro usuario). */
    @Query("SELECT m.id FROM MessageEntity m WHERE m.conversation.id = :convId AND m.sender.id <> :userId AND m.isRead = false")
    List<Long> findUnreadMessageIdsByConversationAndUser(@Param("convId") Long conversationId, @Param("userId") Long userId);

    /** Marca como leídos todos los mensajes de una conversación que NO fueron enviados por el usuario. */
    @Modifying
    @Query("UPDATE MessageEntity m SET m.isRead = true WHERE m.conversation.id = :convId AND m.sender.id <> :userId AND m.isRead = false")
    int markAllAsRead(@Param("convId") Long conversationId, @Param("userId") Long userId);
}
