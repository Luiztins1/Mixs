package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findByName(String name);
}
