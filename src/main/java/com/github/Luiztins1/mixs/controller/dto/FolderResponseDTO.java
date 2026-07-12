package com.github.Luiztins1.mixs.controller.dto;

import com.github.Luiztins1.mixs.model.entity.Album;

import java.util.Set;

public record FolderResponseDTO(
        Integer id,
        String folderName,
        Set<Album> album) {
}
