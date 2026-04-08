package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.CreateConversationRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ConversationResponseDto;
import com.juanCarlos.hardwareHub.dto.response.MessageResponseDto;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.service.ChatService;
import com.juanCarlos.hardwareHub.service.implementation.ChatServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controlador REST para el sistema de mensajería directa.
 *
 * <p>Gestiona las operaciones HTTP. El tiempo real se maneja en {@link ChatWebSocketController}.</p>
 *
 * @see ChatServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
@Tag(name = "Chat", description = "Mensajería directa entre usuarios")
public class ChatRestController {

    private final ChatService chatService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Listar todas las conversaciones del usuario autenticado con resumen")
    public ResponseEntity<List<ConversationResponseDto>> getConversations(Principal principal) {
        UsuarioEntity user = resolveUser(principal);
        return ResponseEntity.ok(chatService.getConversationsForUser(user.getId()));
    }

    @GetMapping("/{id}/messages")
    @Operation(summary = "Historial paginado de mensajes de una conversación")
    public ResponseEntity<Page<MessageResponseDto>> getMessages(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(chatService.getHistory(id, page, size));
    }

    @PostMapping
    @Operation(summary = "Crear o recuperar una conversación con otro usuario")
    public ResponseEntity<ConversationResponseDto> createOrGetConversation(
            @Valid @RequestBody CreateConversationRequestDto request,
            Principal principal
    ) {
        UsuarioEntity user = resolveUser(principal);
        ConversationResponseDto dto = chatService.getOrCreateConversation(user.getId(), request.getTargetUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/{id}/read")
    @Operation(summary = "Marcar como leídos todos los mensajes de una conversación")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id, Principal principal) {
        chatService.markAsRead(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    /**
     * Resuelve la entidad {@link UsuarioEntity} a partir del {@link Principal}
     * (cuyo {@code getName()} es el email del usuario autenticado).
     */
    private UsuarioEntity resolveUser(Principal principal) {
        UsuarioEntity user = usuarioRepository.getByEmail(principal.getName());
        if (user == null) {
            throw new NoSuchElementException("Usuario autenticado no encontrado: " + principal.getName());
        }
        return user;
    }
}

