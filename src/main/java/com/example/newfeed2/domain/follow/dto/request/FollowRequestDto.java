package com.example.newfeed2.domain.follow.dto.request;

import lombok.Getter;

@Getter
public class FollowRequestDto {

    private Long followerId;
    private Long followingId;
}
