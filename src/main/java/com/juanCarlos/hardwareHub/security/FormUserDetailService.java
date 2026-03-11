package com.juanCarlos.hardwareHub.security;

import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class FormUserDetailService implements UserDetailsService {
/*
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioService.getByEmail(email);

        Role role usuario.getRole();

        return User.withUsername(usuario.getEmail())
                .roles(role.getRoleName())
                .password(usuario.getContrasena())
                .build();
    }*/
}
