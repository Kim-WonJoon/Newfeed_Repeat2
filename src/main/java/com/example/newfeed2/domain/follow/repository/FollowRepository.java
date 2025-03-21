package com.example.newfeed2.domain.follow.repository;

import com.example.newfeed2.domain.follow.entity.Follow;
import com.example.newfeed2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);
    void deleteByFollowerAndFollowing(User follower, User following);
}
