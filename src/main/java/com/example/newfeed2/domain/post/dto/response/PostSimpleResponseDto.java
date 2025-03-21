package com.example.newfeed2.domain.post.dto.response;

import com.example.newfeed2.common.BaseTimeEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostSimpleResponseDto extends BaseTimeEntity {

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostSimpleResponseDto(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
