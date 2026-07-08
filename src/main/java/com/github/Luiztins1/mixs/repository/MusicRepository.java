package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Integer> {
}
