package com.github.Luiztins1.mixs.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistResponseDTO(
        Integer id,

        @NotBlank(message = "O nome não pode estar vazio.")
        String name,

        @NotBlank(message = "A data de aniversário não pode estar vazio.")
        String dateOfBirth,

        @NotNull(message = "O campo não pode estar vazio.")
        Integer album) {
}
