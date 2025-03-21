package com.example.newfeed2.domain.comment.service;

import com.example.newfeed2.auth.dto.AuthUser;
import com.example.newfeed2.domain.comment.dto.request.CommentRequestDto;
import com.example.newfeed2.domain.comment.dto.response.CommentResponseDto;
import com.example.newfeed2.domain.comment.entity.Comment;
import com.example.newfeed2.domain.comment.repository.CommentRepository;
import com.example.newfeed2.domain.post.entity.Post;
import com.example.newfeed2.domain.post.repository.PostRepository;
import com.example.newfeed2.domain.user.entity.User;
import com.example.newfeed2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto saveComment(AuthUser authUser, Long postId, CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없음."));

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글을 찾을 수 없음."));

        Comment comment = new Comment(commentRequestDto.getContent(), user, post);
        post.addComment(comment);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment.getId(), savedComment.getContent(), savedComment.getCreatedAt(), savedComment.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll() {
        List<Comment> comments = commentRepository.findAll();

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDtos.add(new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt()));
        }
        return commentResponseDtos;
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글 찾을 수 없음."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalStateException("해당 댓글 찾을 수 없음."));
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    @Transactional
    public CommentResponseDto updateComment(AuthUser authUser, Long postId, Long commentId, CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없음."));

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글 찾을 수 없음."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalStateException("해당 댓글을 찾을 수 없음."));

        if (!comment.getUser().equals(authUser.getId())) {
            throw new IllegalStateException("작성자만 댓글 수정이 가능합니다");
        }

        comment.update(commentRequestDto.getContent());
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    @Transactional
    public void deleteComment(AuthUser authUser, Long postId, Long commentId) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(() -> new IllegalStateException("해당 사용자를 찾을 수 없음."));

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("해당 게시글 찾을 수 없음."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalStateException("해당 댓글을 찾을 수 없음."));

        if (!comment.getUser().getId().equals(authUser.getId())) {
            throw new IllegalStateException("작성자만 댓글 삭제가 가능합니다.");
        }
        commentRepository.delete(comment);
    }
}
