package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.AlbumResponseDTO;
import com.github.Luiztins1.mixs.model.mapper.AlbumMapper;
import com.github.Luiztins1.mixs.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @PreAuthorize("hasAnyHole('ADMIN', 'USER')")
    public ResponseEntity<AlbumResponseDTO> registerAlbum(@RequestBody @Valid AlbumResponseDTO albumResponseDTO){
        var album = albumService.registerAlbum(albumResponseDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(album.getId())
                .toUri();

        return ResponseEntity.created(location).body(AlbumMapper.toDto(album));
    }

    @GetMapping
    @PreAuthorize("hasAnyHole('ADMIN', 'USER')")
    public ResponseEntity<List<AlbumResponseDTO>> listAllAlbums(){
        List<AlbumResponseDTO> albumResponseDTOList =
                albumService.listAllAlbums()
                        .stream()
                        .map(AlbumMapper::toDto)
                        .toList();

        if(albumResponseDTOList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(albumResponseDTOList);
    }
}
