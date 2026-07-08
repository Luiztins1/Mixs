package com.github.Luiztins1.mixs.controller.dto;

import com.github.Luiztins1.mixs.model.entity.Artist;

import java.time.LocalDate;
import java.util.Set;

public record AlbumResponseDTO(
        Integer id,
        String name,
        LocalDate releaseDate,
        Set<Artist> artist,
        String country,
        String description) {
}

