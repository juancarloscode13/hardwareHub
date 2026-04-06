package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {

    /** Busca una conversación existente entre dos usuarios (en cualquier dirección). */
    @Query("SELECT c FROM ConversationEntity c WHERE (c.userA.id = :u1 AND c.userB.id = :u2) OR (c.userA.id = :u2 AND c.userB.id = :u1)")
    Optional<ConversationEntity> findByUsers(@Param("u1") Long u1, @Param("u2") Long u2);

    /** Lista todas las conversaciones en las que participa un usuario. */
    @Query("SELECT c FROM ConversationEntity c WHERE c.userA.id = :userId OR c.userB.id = :userId ORDER BY c.createdAt DESC")
    List<ConversationEntity> findAllByUserId(@Param("userId") Long userId);
}

