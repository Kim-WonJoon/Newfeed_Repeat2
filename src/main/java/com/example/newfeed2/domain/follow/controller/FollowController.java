package com.example.newfeed2.domain.follow.controller;

import com.example.newfeed2.domain.follow.dto.response.FollowResponseDto;
import com.example.newfeed2.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{followerId}/{followingId}")
    public ResponseEntity<FollowResponseDto> follow(@PathVariable Long followerId, @PathVariable Long followingId) {
        return ResponseEntity.ok(followService.followUser(followerId, followingId));
    }

    @DeleteMapping("/follow/{followerId}/{followingId}")
    public void unfollow(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.unfollowUser(followerId, followingId);
    }
}
