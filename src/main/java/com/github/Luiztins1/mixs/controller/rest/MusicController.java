package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/musics")
@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/{track}")
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<List<String>> searchNameMusic(@PathVariable String track){
        var search = musicService.searchNameMusic(track);
        return ResponseEntity.ok(search);
    }
}
