package com.example.newfeed2.domain.follow.dto.response;

import lombok.Getter;

@Getter
public class FollowResponseDto {

    private final Long followerId;
    private final Long followingId;

    public FollowResponseDto(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }
}
