package com.github.Luiztins1.mixs.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseErrorDTO(
        HttpStatus error,
        String message,
        List<FieldErrorResponseDTO> errorList) {
}
