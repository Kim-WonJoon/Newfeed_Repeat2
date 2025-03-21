package com.example.newfeed2.domain.post.service;

import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.domain.post.dto.request.PostSaveRequestDto;
import com.example.newfeed2.domain.post.dto.response.PostDetailResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSaveResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSimpleResponseDto;
import com.example.newfeed2.domain.post.entity.Post;
import com.example.newfeed2.domain.post.repository.PostRepository;
import com.example.newfeed2.domain.user.entity.User;
import com.example.newfeed2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostSaveResponseDto savePost(AuthUser authUser, PostSaveRequestDto postSaveRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없음."));

        Post post = new Post(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent(), user);
        Post savedPost = postRepository.save(post);

        return new PostSaveResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getCreatedAt(), savedPost.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public Page<PostSimpleResponseDto> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllByCreatedAt(pageable)
                .map(post -> new PostSimpleResponseDto(post.getId(), post.getTitle(), post.getCreatedAt(), post.getUpdatedAt()));
    }

    @Transactional(readOnly = true)
    public PostDetailResponseDto findById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음."));
        return new PostDetailResponseDto(post);
    }

    @Transactional
    public PostSaveResponseDto update(AuthUser authUser, Long postId, PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음."));

        if (!post.getUser().getId().equals(authUser.getId())) {
            throw new IllegalStateException("작성자만 게시글 수정이 가능합니다.");
        }

        post.update(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
        return new PostSaveResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt());
    }

    @Transactional
    public void deleteById(AuthUser authUser, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음"));

        if (!post.getUser().getId().equals(authUser.getId())) {
            throw new IllegalStateException("작성자만 게시글 삭제가 가능합니다.");
        }
        postRepository.deleteById(postId);
    }
}
