package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
