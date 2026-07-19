package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.AddAlbumInFolderResponseDTO;
import com.github.Luiztins1.mixs.controller.dto.FolderResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Folder;
import com.github.Luiztins1.mixs.model.mapper.FolderMapper;
import com.github.Luiztins1.mixs.service.FolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;



    @PostMapping("/{folderId}/albums")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> addAlbumInFolder(
            @PathVariable Integer folderId,
            @RequestBody AddAlbumInFolderResponseDTO album){

        folderService.addAlbumInFolder(folderId, album);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FolderResponseDTO> resgisterFolder(@RequestBody @Valid FolderResponseDTO folderResponseDTO) {
        var folder = folderService.registerFolder(folderResponseDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(folder.getId())
                .toUri();

        return ResponseEntity.created(location).body(FolderMapper.toDto(folder));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FolderResponseDTO>> findAll(){
        List<FolderResponseDTO> folderResponseDTOList = folderService.findAll()
                .stream()
                .map(FolderMapper::toDto)
                .toList();
        if(folderResponseDTOList.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(folderResponseDTOList);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FolderResponseDTO> updateFolder(@PathVariable Integer id, @RequestBody @Valid FolderResponseDTO folderResponseDTO){
        Optional<Folder> optionalFolder = folderService.folderUpdate(id, folderResponseDTO);

        if(optionalFolder.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteFolder(@PathVariable Integer id){
        folderService.deleteFolder(id);
        return ResponseEntity.noContent().build();
    }
}
