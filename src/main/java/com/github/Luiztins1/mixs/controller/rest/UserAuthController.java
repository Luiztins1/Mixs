package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.UserAuthResponseDTO;
import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.model.mapper.UserAuthMapper;
import com.github.Luiztins1.mixs.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserAuthResponseDTO> registerUserAuth(@RequestBody UserAuthResponseDTO userAuthResponseDTO) {
        var userAuth = UserAuthMapper.toEntity(userAuthResponseDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAuth.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserAuthMapper.toDto(userAuth));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<List<UserAuthResponseDTO>> findAll() {
        List<UserAuthResponseDTO> userAuthList = userAuthService.findAll()
                .stream()
                .map(UserAuthMapper::toDto)
                .toList();

        if (userAuthList.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userAuthList);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', USER)")
    public ResponseEntity<UserAuthResponseDTO> updateUserAuth(@PathVariable UUID id, @RequestBody UserAuthResponseDTO userAuthResponseDTO) {
        Optional<UserAuth> userAuthDTOOptional = userAuthService.updateUserAuth(id, userAuthResponseDTO);

        if (userAuthDTOOptional.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{login}")
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<Void> cancelUserAuth(@PathVariable String login) {
        userAuthService.cancelUserAuth(login);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<UserAuthResponseDTO> findById(@PathVariable UUID id) {
        return userAuthService.findById(id)
                .map(UserAuthMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasAnyRole('MANAGER', 'USER')")
    public ResponseEntity<UserAuthResponseDTO> findByLogin(String login) {
        Optional<UserAuth> userAuthOptional = userAuthService.findByLogin(login);

        if (userAuthOptional.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
