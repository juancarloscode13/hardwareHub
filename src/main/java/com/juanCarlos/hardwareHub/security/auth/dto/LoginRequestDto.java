package com.juanCarlos.hardwareHub.security.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank
    String email;

    @NotBlank
    String password;
}
