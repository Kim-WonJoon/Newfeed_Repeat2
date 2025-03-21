package com.example.newfeed2.auth.dto.response;

import lombok.Getter;

@Getter
public class AuthResponseDto {

    private final String bearerJwt;

    public AuthResponseDto(String bearerToken) {
        this.bearerJwt = bearerToken;
    }
}
