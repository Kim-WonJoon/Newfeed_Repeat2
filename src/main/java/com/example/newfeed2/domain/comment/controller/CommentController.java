package com.example.newfeed2.domain.comment.controller;

import com.example.newfeed2.auth.annotation.Auth;
import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.domain.comment.dto.request.CommentRequestDto;
import com.example.newfeed2.domain.comment.dto.response.CommentResponseDto;
import com.example.newfeed2.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentResponseDto> saveComment(@Auth AuthUser authUser, @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.saveComment(authUser, postId, commentRequestDto));
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getOneComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.findById(postId, commentId));
    }

    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@Auth AuthUser authUser,@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(authUser, postId, commentId, commentRequestDto));
    }

    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public void deleteComment(@Auth AuthUser authUser, @PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(authUser, postId, commentId);
    }
}
