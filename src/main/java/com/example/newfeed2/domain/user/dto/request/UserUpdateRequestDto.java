package com.example.newfeed2.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private String passwordCheck;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8}$",
            message = "비밀번호는 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하여 8자 이상이어야 합니다.")
    private String password;
}
