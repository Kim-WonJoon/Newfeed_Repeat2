package com.example.newfeed2.domain.post.dto.response;

import com.example.newfeed2.common.BaseTimeEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResponseDto extends BaseTimeEntity {

    private final Long id;
    private final String title;
    private final String content;
    // comment
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostDetailResponseDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
