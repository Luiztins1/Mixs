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

    public List<String> searchNameArtist(String title){
        var result = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/database/search")
                        .queryParam("title", title)
                        .queryParam("type", "artist")
                        .build())
                .retrieve()
                .body(DiscogsSearchArtistResponseDTO.class);

        return result.results()
                .stream()
                .map(DiscogsResultArtistDTO::title)
                .distinct()
                .sorted()
                .toList();

    }}
