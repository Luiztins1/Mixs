package com.github.Luiztins1.mixs.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record MusicResponseDTO(
        Integer id,

        @NotBlank(message = "O nome da música não pode estar vazia.")
        String track,

        @NotBlank(message = "O estilo da música não pode estar vazia.")
        String style,

        @NotNull(message = "O album não pode ser vazio.")
        List<Integer> albumList)
{
}
