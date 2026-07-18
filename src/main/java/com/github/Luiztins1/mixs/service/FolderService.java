package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.FolderResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.UserResponseDTO;
import com.github.Luiztins1.mixs.exceptions.DuplicateException;
import com.github.Luiztins1.mixs.exceptions.NotFoundException;
import com.github.Luiztins1.mixs.model.entity.Folder;
import com.github.Luiztins1.mixs.model.entity.User;
import com.github.Luiztins1.mixs.model.mapper.FolderMapper;
import com.github.Luiztins1.mixs.model.mapper.UserMapper;
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

    @Transactional
    public Folder registerFolder(FolderResponseDTO folderResponseDTO){
        var folder = FolderMapper.toEntity(folderResponseDTO);
        var folderValidate = folderRepository.findByName(folder.getFolderName())
                .orElseThrow(() -> new DuplicateException("Pasta já cadastrada."));

        return folderRepository.save(folder);

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
