package com.github.Luiztins1.mixs.controller.dto;

import java.time.LocalDate;

public record AlbumDTO(
        Integer id,
        String name,
        LocalDate releaseDate,
        Integer artist,
        String country,
        String description) {
}

