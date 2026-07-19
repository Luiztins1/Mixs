package com.github.Luiztins1.mixs.controller.dto;

import com.github.Luiztins1.mixs.model.entity.FolderItem;

import java.util.List;

public record FolderResponseDTO(
        Integer id,
        String folderName,
        List<FolderItem> folderItems) {
}
