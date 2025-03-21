package com.example.newfeed2.domain.post.controller;

import com.example.newfeed2.domain.post.dto.request.PostSaveRequestDto;
import com.example.newfeed2.domain.post.dto.response.PostDetailResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSaveResponseDto;
import com.example.newfeed2.domain.post.dto.response.PostSimpleResponseDto;
import com.example.newfeed2.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.ok(postService.savePost(postSaveRequestDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostSimpleResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{postTitle}")
    public ResponseEntity<PostDetailResponseDto> getOnePost(@PathVariable String title) {
        return ResponseEntity.ok(postService.findByTitle(title));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostSaveResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.ok(postService.update(postId, postSaveRequestDto));
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
    }
}
