package com.github.Luiztins1.mixs.controller.dto;

public record AddAlbumInFolderResponseDTO(
        Integer folderId,
        Integer albumId,
        String albumName) {
}
