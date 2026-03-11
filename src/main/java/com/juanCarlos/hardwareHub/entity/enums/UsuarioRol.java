package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Valores de rol de usuario.
 *
 * @author Juan Carlos
 */

@AllArgsConstructor
@Getter
public enum UsuarioRol {

    ROL_USUARIO("USUARIO"),
    ROL_ADMIN("ADMIN");

    private String desc;
}
