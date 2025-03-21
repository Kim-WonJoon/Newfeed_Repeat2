package com.example.newfeed2.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}
