package com.github.Luiztins1.mixs.controller.rest;

import com.github.Luiztins1.mixs.controller.dto.UserResponseDTO;
import com.github.Luiztins1.mixs.model.entity.User;
import com.github.Luiztins1.mixs.model.mapper.UserMapper;
import com.github.Luiztins1.mixs.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserResponseDTO userResponseDTO){
        var user = userService.registerUser(userResponseDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserMapper.toDto(user));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> userResponseDTOS = userService.findAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();

        if(userResponseDTOS.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userResponseDTOS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponseDTO> userUpdate(@PathVariable  Integer id, @RequestBody @Valid UserResponseDTO userResponseDTO){
        Optional<User> user = userService.userUpdate(id, userResponseDTO);

        if(user.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Integer id){
        return userService.findById(id)
                .map(UserMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
