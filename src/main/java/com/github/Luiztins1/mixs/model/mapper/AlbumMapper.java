package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.AlbumResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Album;

public class AlbumMapper {

    public static AlbumResponseDTO toDto (Album album){
        if(album == null) return null;

        return new AlbumResponseDTO(
                album.getId(),
                album.getName(),
                album.getYear(),
                album.getArtist(),
                album.getMusicList(),
                album.getCountry(),
                album.getGenre(),
                album.getDescription()
        );
    }

    public static Album toEntity (AlbumResponseDTO albumResponseDTO){
        if(albumResponseDTO == null) return null;

        Album album = new Album();

        album.setId(albumResponseDTO.id());
        album.setName(albumResponseDTO.name());
        album.setYear(albumResponseDTO.year());
        album.setArtist(albumResponseDTO.artist());
        album.setMusicList(albumResponseDTO.musicList());
        album.setCountry(albumResponseDTO.country());
        album.setCountry(albumResponseDTO.country());
        album.setDescription(albumResponseDTO.description());

        return album;
    }
}
