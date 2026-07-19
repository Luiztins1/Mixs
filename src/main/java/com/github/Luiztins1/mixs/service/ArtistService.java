package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.*;
import com.github.Luiztins1.mixs.exceptions.NotFoundException;
import com.github.Luiztins1.mixs.model.entity.Artist;
import com.github.Luiztins1.mixs.model.entity.Folder;
import com.github.Luiztins1.mixs.model.entity.FolderItem;
import com.github.Luiztins1.mixs.model.enums.ItemType;
import com.github.Luiztins1.mixs.repository.ArtistRepository;
import com.github.Luiztins1.mixs.repository.FolderItemRepository;
import com.github.Luiztins1.mixs.repository.FolderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final FolderRepository folderRepository;
    private final FolderItemRepository folderItemRepository;
    private final RestClient restClient;

    @Transactional
    public void addArtistInFolder(Integer folderId, AddArtistInFolderResponseDTO artistDTO){
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException("Pasta não encontrada."));

        FolderItem folderItem = new FolderItem();
        folderItem.setName(artistDTO.artistName());
        folderItem.setExternalId(artistDTO.artistId().toString());
        folderItem.setItemType(ItemType.ARTIST);
        folderItem.setFolder(folder);

        folderItemRepository.save(folderItem);
    }

    public List<DiscogsResultArtistDTO> searchNameArtist(String artistName){
        var result = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/database/search")
                        .queryParam("q", artistName)
                        .queryParam("type", "artist")
                        .build())
                .retrieve()
                .body(DiscogsSearchArtistResponseDTO.class);

        return result.results()
                .stream()
                .distinct()
                .toList();

    }}
