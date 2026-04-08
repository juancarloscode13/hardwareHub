package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.response.ConversationResponseDto;
import com.juanCarlos.hardwareHub.dto.response.MessageResponseDto;
import com.juanCarlos.hardwareHub.entity.ConversationEntity;
import com.juanCarlos.hardwareHub.entity.MessageEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.repository.ConversationRepository;
import com.juanCarlos.hardwareHub.repository.MessageRepository;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.service.ChatService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementacion del servicio de la entidad ConversationEntity
 *
 * @see ChatService
 * @author Juan Carlos
 */
@Service
@Transactional
@AllArgsConstructor
public class ChatServiceImplementation implements ChatService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ConversationResponseDto getOrCreateConversation(Long currentUserId, Long targetUserId) {
        if (currentUserId.equals(targetUserId)) {
            throw new IllegalArgumentException("No puedes iniciar una conversación contigo mismo");
        }

        UsuarioEntity currentUser = usuarioRepository.findById(currentUserId)
                .orElseThrow(() -> new NoSuchElementException("Usuario autenticado no encontrado"));
        UsuarioEntity targetUser = usuarioRepository.findById(targetUserId)
                .orElseThrow(() -> new NoSuchElementException("Usuario destino no encontrado con id: " + targetUserId));

        ConversationEntity conversation = conversationRepository.findByUsers(currentUserId, targetUserId)
                .orElseGet(() -> {
                    ConversationEntity newConv = new ConversationEntity();
                    newConv.setUserA(currentUser);
                    newConv.setUserB(targetUser);
                    newConv.setCreatedAt(LocalDateTime.now());
                    return conversationRepository.save(newConv);
                });

        return toConversationDto(conversation, currentUserId);
    }

    @Override
    public MessageResponseDto sendMessage(String senderEmail, Long conversationId, String content) {
        UsuarioEntity sender = usuarioRepository.getByEmail(senderEmail);
        if (sender == null) {
            throw new NoSuchElementException("Usuario emisor no encontrado: " + senderEmail);
        }

        ConversationEntity conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new NoSuchElementException("Conversación no encontrada con id: " + conversationId));

        // Verificar que el emisor pertenece a la conversación
        if (!conversation.getUserA().getId().equals(sender.getId())
                && !conversation.getUserB().getId().equals(sender.getId())) {
            throw new IllegalArgumentException("No perteneces a esta conversación");
        }

        MessageEntity message = new MessageEntity();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        message.setRead(false);

        MessageEntity saved = messageRepository.save(message);
        return toMessageDto(saved);
    }

    @Override
    public Page<MessageResponseDto> getHistory(Long conversationId, int page, int size) {
        if (!conversationRepository.existsById(conversationId)) {
            throw new NoSuchElementException("Conversación no encontrada con id: " + conversationId);
        }
        Page<MessageEntity> messages = messageRepository
                .findByConversationIdOrderBySentAtDesc(conversationId, PageRequest.of(page, size));
        return messages.map(this::toMessageDto);
    }

    @Override
    public List<Long> markAsRead(Long conversationId, String readerEmail) {
        UsuarioEntity reader = usuarioRepository.getByEmail(readerEmail);
        if (reader == null) {
            throw new NoSuchElementException("Usuario no encontrado: " + readerEmail);
        }

        ConversationEntity conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new NoSuchElementException("Conversación no encontrada con id: " + conversationId));

        // Seguridad: solo participantes pueden marcar como leído
        if (!conversation.getUserA().getId().equals(reader.getId())
                && !conversation.getUserB().getId().equals(reader.getId())) {
            throw new IllegalArgumentException("No perteneces a esta conversación");
        }

        List<Long> unreadMessageIds = messageRepository
                .findUnreadMessageIdsByConversationAndUser(conversationId, reader.getId());

        if (unreadMessageIds == null || unreadMessageIds.isEmpty()) {
            return Collections.emptyList();
        }

        messageRepository.markAllAsRead(conversationId, reader.getId());
        return unreadMessageIds;
    }

    @Override
    public List<ConversationResponseDto> getConversationsForUser(Long userId) {
        if (!usuarioRepository.existsById(userId)) {
            throw new NoSuchElementException("Usuario no encontrado con id: " + userId);
        }
        return conversationRepository.findAllByUserId(userId).stream()
                .map(conv -> toConversationDto(conv, userId))
                .toList();
    }

    @Override
    public String getRecipientEmail(Long conversationId, String senderEmail) {
        ConversationEntity conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new NoSuchElementException("Conversación no encontrada con id: " + conversationId));

        if (conversation.getUserA().getEmail().equals(senderEmail)) {
            return conversation.getUserB().getEmail();
        }
        return conversation.getUserA().getEmail();
    }

    private MessageResponseDto toMessageDto(MessageEntity entity) {
        MessageResponseDto dto = new MessageResponseDto();
        dto.setId(entity.getId());
        dto.setConversationId(entity.getConversation().getId());
        dto.setSenderId(entity.getSender().getId());
        dto.setSenderNombre(entity.getSender().getNombre());
        dto.setContent(entity.getContent());
        dto.setSentAt(entity.getSentAt());
        dto.setRead(entity.isRead());
        return dto;
    }

    private ConversationResponseDto toConversationDto(ConversationEntity entity, Long currentUserId) {
        UsuarioEntity otherUser = entity.getUserA().getId().equals(currentUserId)
                ? entity.getUserB()
                : entity.getUserA();

        ConversationResponseDto dto = new ConversationResponseDto();
        dto.setId(entity.getId());
        dto.setOtherUserId(otherUser.getId());
        dto.setOtherUserNombre(otherUser.getNombre());
        dto.setOtherUserIconoPerfil(otherUser.getIconoPerfil());

        messageRepository.findFirstByConversationIdOrderBySentAtDesc(entity.getId())
                .ifPresent(lastMsg -> {
                    dto.setLastMessageContent(lastMsg.getContent());
                    dto.setLastMessageAt(lastMsg.getSentAt());
                });

        dto.setUnreadCount(messageRepository.countUnreadByConversationAndUser(entity.getId(), currentUserId));
        return dto;
    }
}

