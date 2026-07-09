package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.DiscogsResultArtistDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsResultMusicDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchArtistResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsSearchMusicResponseDTO;
import com.github.Luiztins1.mixs.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final RestClient restClient;

    public List<String> searchNameMusic(String track){
        var result = restClient.get()
                .uri("/database/search?q={query}&type=track", track)
                .retrieve()
                .body(DiscogsSearchMusicResponseDTO.class);

        return result.results()
                .stream()
                .map(DiscogsResultMusicDTO::track)
                .toList();

    }
}
