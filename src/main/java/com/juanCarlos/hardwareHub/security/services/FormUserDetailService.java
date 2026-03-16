package com.juanCarlos.hardwareHub.security.services;

import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormUserDetailService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsuarioEntity usuario = usuarioService.getByEmail(email);
        UsuarioRol rol = usuario.getRol();

        return User.withUsername(usuario.getEmail())
                .roles(rol.getDesc())
                .password(usuario.getContrasena())
                .build();
    }

    public UsuarioEntity getCurrentUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;

        UsuarioEntity usuarioAutenticado = (UsuarioEntity) auth.getPrincipal();
        return usuarioService.getByEmail(usuarioAutenticado.getEmail());
    }
}
