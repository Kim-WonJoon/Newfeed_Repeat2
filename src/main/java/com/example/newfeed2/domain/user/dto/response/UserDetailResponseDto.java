package com.example.newfeed2.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserDetailResponseDto {

    private final Long id;
    private final String email;

    public UserDetailResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
