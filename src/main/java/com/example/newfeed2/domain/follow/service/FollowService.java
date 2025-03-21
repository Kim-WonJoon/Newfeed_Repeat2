package com.example.newfeed2.domain.follow.service;

import com.example.newfeed2.domain.follow.dto.response.FollowResponseDto;
import com.example.newfeed2.domain.follow.entity.Follow;
import com.example.newfeed2.domain.follow.repository.FollowRepository;
import com.example.newfeed2.domain.user.entity.User;
import com.example.newfeed2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowResponseDto followUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalStateException("팔로우 하는 유저를 찾을 수 없음."));
        User following = userRepository.findById(followingId).orElseThrow(() -> new IllegalStateException("팔로잉 하는 유저를 찾을 수 없음."));

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new IllegalStateException("이미 팔로우 중입니다.");
        }

        Follow follow = new Follow(follower, following);
        followRepository.save(follow);
        return new FollowResponseDto(follower.getId(), following.getId());
    }

    public void unfollowUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalStateException("팔로우 하는 유저를 찾을 수 없음."));
        User following = userRepository.findById(followingId).orElseThrow(() -> new IllegalStateException("팔로잉 하는 유저를 찾을 수 없음."));

        followRepository.deleteByFollowerAndFollowing(follower, following);
    }
}
