package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.DiscogsResultAlbumDTO;
import com.github.Luiztins1.mixs.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/albums")
@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<DiscogsResultAlbumDTO>> searchAlbum(@PathVariable String name){
        var search = albumService.searchNameAlbum(name);

        return ResponseEntity.ok(search);
    }

}
