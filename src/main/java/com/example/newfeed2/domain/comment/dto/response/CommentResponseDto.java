package com.example.newfeed2.domain.comment.dto.response;

import com.example.newfeed2.common.BaseTimeEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto extends BaseTimeEntity {

    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
