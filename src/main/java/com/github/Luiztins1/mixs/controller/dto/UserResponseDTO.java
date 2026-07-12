package com.github.Luiztins1.mixs.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UserResponseDTO(
        Integer id,

        @NotBlank(message = "O nome não pode estar vazio.")
        String name) {
}
