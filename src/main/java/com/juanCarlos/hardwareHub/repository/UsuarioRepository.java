package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {
	UsuarioEntity getByEmail(String email);

	/** Comprueba si ya existe un usuario con el email dado. */
	boolean existsByEmail(String email);

	/** Devuelve todos los usuarios que siguen al usuario con el id dado. */
	@Query("SELECT u.seguidores FROM UsuarioEntity u WHERE u.id = :userId")
	Set<UsuarioEntity> findSeguidoresByUserId(@Param("userId") Long userId);

	/** Devuelve todos los usuarios que el usuario con el id dado sigue. */
	@Query("SELECT u.usuariosSeguidos FROM UsuarioEntity u WHERE u.id = :userId")
	Set<UsuarioEntity> findSeguidosByUserId(@Param("userId") Long userId);

	/** Comprueba si id_seguidor ya sigue a id_seguido. */
	@Query("SELECT COUNT(u) > 0 FROM UsuarioEntity u JOIN u.usuariosSeguidos s WHERE u.id = :followerId AND s.id = :followedId")
	boolean isFollowing(@Param("followerId") Long followerId, @Param("followedId") Long followedId);
}
