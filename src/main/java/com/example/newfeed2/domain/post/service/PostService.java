package com.example.newfeed2.domain.post.service;

import com.example.newfeed2.domain.post.dto.request.PostSaveRequestDto;
import com.example.newfeed2.domain.post.dto.response.PostDetailResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSaveResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSimpleResponseDto;
import com.example.newfeed2.domain.post.entity.Post;
import com.example.newfeed2.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostSaveResponseDto savePost(PostSaveRequestDto postSaveRequestDto) {
        Post post = new Post(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
        Post savedPost = postRepository.save(post);
        return new PostSaveResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getCreatedAt(), savedPost.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<PostSimpleResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostSimpleResponseDto> postSimpleResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postSimpleResponseDtos.add(new PostSimpleResponseDto(post.getId(), post.getTitle(), post.getCreatedAt(), post.getUpdatedAt()));
        }
        return postSimpleResponseDtos;
    }

    @Transactional(readOnly = true)
    public PostDetailResponseDto findByTitle(String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음."));
        return new PostDetailResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt());
    }

    @Transactional
    public PostSaveResponseDto update(Long postId, PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음."));
        post.update(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
        return new PostSaveResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt());
    }

    public void deleteById(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalStateException("해당 게시글을 찾을 수 없음");
        }
        postRepository.deleteById(postId);
    }
}
