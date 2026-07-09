package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.DiscogsResultArtistDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchAlbumResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchArtistResponseDTO;
import com.github.Luiztins1.mixs.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final RestClient restClient;

    public List<String> searchNameArtist(String name){
        var result = restClient.get()
                .uri("/database/search?q={query}&type=artist", name)
                .retrieve()
                .body(DiscogsSearchArtistResponseDTO.class);

        return result.results()
                .stream()
                .map(DiscogsResultArtistDTO::name)
                .toList();

    }}
