package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.AlbumResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsResultAlbumDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchAlbumResponseDTO;
import com.github.Luiztins1.mixs.exceptions.DuplicateException;
import com.github.Luiztins1.mixs.exceptions.NotFoundException;
import com.github.Luiztins1.mixs.model.entity.Album;
import com.github.Luiztins1.mixs.model.mapper.AlbumMapper;
import com.github.Luiztins1.mixs.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final RestClient restClient;

    public List<String> searchNameAlbum(String name){

        var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/database/search")
                            .queryParam("q", name)
                            .queryParam("type", "release")
                            .build())
                    .retrieve()
                    .body(DiscogsSearchAlbumResponseDTO.class);

        return response.results()
                .stream()
                .map(DiscogsResultAlbumDTO::title)
                .distinct()
                .sorted()
                .toList();
    }

    @Transactional
    public Album registerAlbum(AlbumResponseDTO albumResponseDTO){
        var album = AlbumMapper.toEntity(albumResponseDTO);
        var albumValidate = albumRepository.findByName(album.getName())
                .orElseThrow(() -> new DuplicateException("Album já registrado."));

        return albumRepository.save(albumValidate);
    }

    public List<Album> listAllAlbums(){
        return albumRepository.findAll();
    }
}
