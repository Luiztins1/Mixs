package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.DiscogsResultAlbumDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchAlbumResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final RestClient restClient;

    public List<DiscogsResultAlbumDTO> searchNameAlbum(String name){

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
                .distinct()
                .toList();
    }

   /* @Transactional
    public List<String> registerInAlbum(String name){
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/database/search")
                        .queryParam("q", name)
                        .queryParam("type", "release")
                        .build())
                .retrieve()
                .body(DiscogsSearchAlbumResponseDTO.class);

        return response.results().
                stream()
                .map(AlbumResponseDTO::name)

    }*/

}
