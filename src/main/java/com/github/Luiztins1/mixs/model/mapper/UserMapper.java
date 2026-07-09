package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.UserResponseDTO;
import com.github.Luiztins1.mixs.model.entity.User;

public class UserMapper {

    public static UserResponseDTO toDto (User user){
        if(user == null) return null;

        return new UserResponseDTO(
                user.getId(),
                user.getName()
        );
    }

    public static User toEntity (UserResponseDTO userResponseDTO){
        if(userResponseDTO == null) return null;

        User user = new User();

        user.setId(userResponseDTO.id());
        user.setName(userResponseDTO.name());

        return user;
    }
}
