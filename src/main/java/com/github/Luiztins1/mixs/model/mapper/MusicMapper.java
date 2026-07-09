package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.MusicResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Music;

public class MusicMapper {

    public static MusicResponseDTO toDto (Music music){
        if(music == null) return null;

        return new MusicResponseDTO(
                music.getId(),
                music.getNameSong(),
                music.getBand(),
                music.getReleaseDate()
        );
    }

    public static Music toEntity (MusicResponseDTO musicResponseDTO){
        if(musicResponseDTO == null) return null;

        Music music = new Music();

        music.setId(musicResponseDTO.id());
        music.setNameSong(musicResponseDTO.nameSong());
        music.setBand(musicResponseDTO.band());
        music.setReleaseDate(musicResponseDTO.releaseDate());

        return music;
    }
}
