package com.github.Luiztins1.mixs.model.mapper;

import com.github.Luiztins1.mixs.controller.dto.UserAuthResponseDTO;
import com.github.Luiztins1.mixs.model.entity.UserAuth;

public class UserAuthMapper {

    public static UserAuthResponseDTO toDto (UserAuth userAuth){
        if(userAuth == null) return null;

        return new UserAuthResponseDTO(
                userAuth.getId(),
                userAuth.getLogin(),
                userAuth.getPassword(),
                userAuth.getEmail(),
                userAuth.getRoles()
        );
    }

    public static UserAuth toEntity(UserAuthResponseDTO userAuthResponseDTO){
        if(userAuthResponseDTO == null) return null;

        UserAuth userAuth = new UserAuth();

        userAuth.setId(userAuthResponseDTO.id());
        userAuth.setLogin(userAuthResponseDTO.login());
        userAuth.setPassword(userAuthResponseDTO.password());
        userAuth.setEmail(userAuthResponseDTO.email());
        userAuth.setRoles(userAuthResponseDTO.roles());

        return userAuth;
    }
}
