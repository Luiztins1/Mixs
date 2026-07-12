package com.github.Luiztins1.mixs.controller.dto;

import com.github.Luiztins1.mixs.model.entity.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record AlbumResponseDTO(
        Integer id,

        @NotBlank(message = "O nome não pode estar vazio.")
        String name,

        @NotNull(message = "A data não pode estar vazia.")
        LocalDate releaseDate,

        @NotNull(message = "A lista de artista não pode estar vazia.")
        Set<Artist> artist,

        @NotBlank(message = "O pais não pode estar vazio.")
        String country,

        @NotBlank(message = "A descrição não pode estar vazia.")
        String description) {
}

