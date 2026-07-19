package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.ArtistResponseDTO;
import com.github.Luiztins1.mixs.model.entity.Artist;

public class ArtistMapper {

    public static ArtistResponseDTO toDto (Artist artist){
        if(artist == null) return null;

        return new ArtistResponseDTO(
                artist.getId(),
                artist.getName()
        );
    }

    public static Artist toEntity (ArtistResponseDTO artistResponseDTO){
        if(artistResponseDTO == null) return null;

        Artist artist = new Artist();

        artist.setId(artistResponseDTO.id());
        artist.setName(artistResponseDTO.name());

        return artist;
    }
}
