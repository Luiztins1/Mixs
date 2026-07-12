package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.FolderResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Folder;

public class FolderMapper {

    public static FolderResponseDTO toDto (Folder folder){
        if(folder == null) return null;

        return new FolderResponseDTO(
                folder.getId(),
                folder.getFolderName(),
                folder.getAlbum()
        );
    }

    public static Folder toEntity (FolderResponseDTO folderResponseDTO){
        if(folderResponseDTO == null) return null;

        Folder folder = new Folder();

        folder.setId(folderResponseDTO.id());
        folder.setAlbum(folderResponseDTO.album());

        return folder;
    }
}
