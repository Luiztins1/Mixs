package com.github.Luiztins1.mixs.controller.dto;

import java.time.LocalDate;

public record MusicDTO(
        Integer id,
        String name,
        String band,
        LocalDate releaseDate) {
}
