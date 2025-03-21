package com.example.newfeed2.domain.post.controller;

import com.example.newfeed2.auth.annotation.Auth;
import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.domain.post.dto.request.PostSaveRequestDto;
import com.example.newfeed2.domain.post.dto.response.PostDetailResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSaveResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSimpleResponseDto;
import com.example.newfeed2.domain.post.entity.Post;
import com.example.newfeed2.domain.post.repository.PostRepository;
import com.example.newfeed2.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> savePost(@Auth AuthUser authUser, @Valid @RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.ok(postService.savePost(authUser, postSaveRequestDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostSimpleResponseDto>> getPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(postService.getPosts(page, size));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponseDto> getOnePost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostSaveResponseDto> updatePost(@Auth AuthUser authUser, @PathVariable Long postId, @Valid @RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.ok(postService.update(authUser, postId, postSaveRequestDto));
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@Auth AuthUser authUser, @PathVariable Long postId) {
        postService.deleteById(authUser, postId);
    }
}
