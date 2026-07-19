package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.AddAlbumInFolderResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.FolderResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.UserResponseDTO;
import com.github.Luiztins1.mixs.exceptions.DuplicateException;
import com.github.Luiztins1.mixs.exceptions.NotFoundException;
import com.github.Luiztins1.mixs.model.entity.Folder;
import com.github.Luiztins1.mixs.model.entity.FolderItem;
import com.github.Luiztins1.mixs.model.entity.User;
import com.github.Luiztins1.mixs.model.enums.ItemType;
import com.github.Luiztins1.mixs.model.mapper.FolderMapper;
import com.github.Luiztins1.mixs.model.mapper.UserMapper;
import com.github.Luiztins1.mixs.repository.FolderItemRepository;
import com.github.Luiztins1.mixs.repository.FolderRepository;
import com.github.Luiztins1.mixs.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final FolderItemRepository folderItemRepository;

    @Transactional
    public void addAlbumInFolder(Integer folderId, AddAlbumInFolderResponseDTO album){
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException("Pasta não encontrada."));

        FolderItem folderItem = new FolderItem();
        folderItem.setName(album.albumName());
        folderItem.setExternalId(album.albumId().toString());
        folderItem.setItemType(ItemType.ALBUM);
        folderItem.setFolder(folder);

        folderItemRepository.save(folderItem);
    }

    @Transactional
    public Folder registerFolder(FolderResponseDTO folderResponseDTO){
        var folder = FolderMapper.toEntity(folderResponseDTO);

        if(folder.getId() != null){
            Optional<Folder> folderValidate = folderRepository.findById(folder.getId());
            if(folderValidate.isPresent()) throw new DuplicateException("Pasta já criada.");
        }

        return folderRepository.save(folder);

    }

    public List<Folder> findAll(){
        return folderRepository.findAll();
    }

    @Transactional
    public Optional<Folder> folderUpdate(Integer id, FolderResponseDTO folderResponseDTO){
        return folderRepository.findById(id)
                .map(folder -> {
                    if(folder.getId() == null) throw new NotFoundException("Pasta não encontrado.");

                    folder.setFolderName(folderResponseDTO.folderName());
                    return folderRepository.save(folder);
                });
    }

    @Transactional
    public void deleteFolder(Integer id){
        var folderValidate = folderRepository.findById(id);

        if(folderValidate.isEmpty()) throw new NotFoundException("Pasta não encontrado.");

        folderRepository.deleteById(id);
    }
}
