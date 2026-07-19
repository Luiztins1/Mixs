package com.github.Luiztins1.mixs.controller.dto;

import java.util.Set;

public record FolderResponseDTO(
        Integer id,
        String folderName,
        Set<Album> album) {
}
