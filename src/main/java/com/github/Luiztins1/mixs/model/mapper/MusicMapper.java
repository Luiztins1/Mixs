package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.MusicResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Music;

public class MusicMapper {

    public static MusicResponseDTO toDto (Music music){
        if(music == null) return null;

        return new MusicResponseDTO(
                music.getId(),
                music.getTrack(),
                music.getStyle(),
                music.getAlbumList()
        );
    }

    public static Music toEntity (MusicResponseDTO musicResponseDTO){
        if(musicResponseDTO == null) return null;

        Music music = new Music();

        music.setId(musicResponseDTO.id());
        music.setTrack(musicResponseDTO.track());
        music.setStyle(musicResponseDTO.style());
        music.setAlbumList(musicResponseDTO.albumList());

        return music;
    }
}
