package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.AddArtistInFolderResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.DiscogsResultArtistDTO;
import com.github.Luiztins1.mixs.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/artists")
@RestController
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping("/{folderId}/artists")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> addArtistInFolder(
            @PathVariable Integer folderId,
            @RequestBody AddArtistInFolderResponseDTO artist){

        artistService.addArtistInFolder(folderId, artist);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<DiscogsResultArtistDTO>> searchNameArtist(@PathVariable String name){
        var search = artistService.searchNameArtist(name);
        return ResponseEntity.ok(search);
    }
}
