package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/artists")
@RestController
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<List<String>> searchNameArtist(@PathVariable String name){
        var search = artistService.searchNameArtist(name);
        return ResponseEntity.ok(search);
    }
}
