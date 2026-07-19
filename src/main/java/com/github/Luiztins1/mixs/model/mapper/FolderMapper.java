package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.FolderItemResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.FolderResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Folder;
import com.github.Luiztins1.mixs.model.entity.FolderItem;

import java.util.Collections;
import java.util.List;

public class FolderMapper {

    public static FolderResponseDTO toDto (Folder folder){
        if(folder == null) return null;

        List<FolderItemResponseDTO> itemsDto = Collections.emptyList();

        if(folder.getFolderItems() != null){
            itemsDto = folder.getFolderItems()
                    .stream()
                    .map(item -> new FolderItemResponseDTO(
                            item.getName(),
                            item.getItemType()
                    ))
                    .toList();
        }

        return new FolderResponseDTO(
                folder.getId(),
                folder.getFolderName(),
                itemsDto
        );
    }

    public static Folder toEntity (FolderResponseDTO folderResponseDTO){
        if(folderResponseDTO == null) return null;

        Folder folder = new Folder();
        folder.setId(folderResponseDTO.id());
        folder.setFolderName(folderResponseDTO.folderName());

        if(folderResponseDTO.folderItems() != null){
            List<FolderItem> items = folderResponseDTO.folderItems()
                    .stream()
                    .map(itemDTO -> {
                        FolderItem folderItem = new FolderItem();
                        folderItem.setName(itemDTO.name());
                        folderItem.setItemType(itemDTO.itemType());
                        folderItem.setFolder(folder);

                        return folderItem;
                    })
                    .toList();

            folder.setFolderItems(items);
        }

        return folder;
    }
}
