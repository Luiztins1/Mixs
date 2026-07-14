package com.github.Luiztins1.mixs.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record UserAuthResponseDTO(
        UUID id,

        @NotBlank(message = "O login é obrigatório e não pode estar em branco.")
        String login,

        @NotBlank(message = "A senha é obrigatória e não pode estar em branco.")
        String password,

        @Email(message = "Email inválido.")
        String email,

        @NotEmpty(message = "Adicione corretamente as roles.")
        List<String> roles) {
}
