package com.github.Luiztins1.mixs.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MusicResponseDTO(
        Integer id,

        @NotBlank(message = "O nome da música não pode estar vazia.")
        String nameSong,

        @NotBlank(message = "O nome da banda não pode estar vazio.")
        String band,

        @NotNull(message = "A data não pode estar vazia.")
        LocalDate releaseDate) {
}
