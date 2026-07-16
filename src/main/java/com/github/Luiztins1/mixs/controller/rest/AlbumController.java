package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/albums")
@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<String>> searchAlbum(@PathVariable String name){
        var search = albumService.searchNameAlbum(name);

        return ResponseEntity.ok(search);
    }
}
