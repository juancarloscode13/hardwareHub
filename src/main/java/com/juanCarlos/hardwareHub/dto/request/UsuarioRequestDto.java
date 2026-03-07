package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    private String nombre;
    private String email;
    private String contrasena;
}
