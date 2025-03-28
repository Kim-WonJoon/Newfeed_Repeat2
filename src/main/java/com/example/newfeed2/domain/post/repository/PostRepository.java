package com.example.newfeed2.domain.post.repository;

import com.example.newfeed2.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCreatedAt(Pageable pageable);

}
