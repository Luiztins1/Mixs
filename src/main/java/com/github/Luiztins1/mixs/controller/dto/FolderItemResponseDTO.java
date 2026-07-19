package com.github.Luiztins1.mixs.controller.dto;

import com.github.Luiztins1.mixs.model.enums.ItemType;

public record FolderItemResponseDTO(
        String name,
        ItemType itemType) {
}
