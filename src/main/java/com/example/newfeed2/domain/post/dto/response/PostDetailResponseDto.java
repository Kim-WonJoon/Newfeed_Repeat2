package com.example.newfeed2.domain.post.dto.response;

import com.example.newfeed2.common.BaseTimeEntity;
import com.example.newfeed2.domain.comment.dto.response.CommentResponseDto;
import com.example.newfeed2.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDetailResponseDto extends BaseTimeEntity {

    private final Long id;
    private final String title;
    private final String content;
    private final List<CommentResponseDto> comments;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostDetailResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
