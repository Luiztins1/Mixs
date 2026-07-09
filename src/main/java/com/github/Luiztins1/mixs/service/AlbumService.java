package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.DiscogsResultAlbumDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchAlbumResponseDTO;
import com.github.Luiztins1.mixs.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final RestClient restClient;

    public List<String> searchAlbum(String name){

        var response = restClient.get()
                    .uri("/database/search?q={query}&type=release", name)
                    .header("User-Agent", "MixsApp/1.0")
                    .retrieve()
                    .body(DiscogsSearchAlbumResponseDTO.class);

        return response.results()
                .stream()
                .map(DiscogsResultAlbumDTO::title)
                .toList();
    }
}
