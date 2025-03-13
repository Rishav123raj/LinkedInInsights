package com.example.LinkedInInsights.controller;

import com.example.LinkedInInsights.model.Comment;
import com.example.LinkedInInsights.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Fetches comments for a given post ID with pagination.
     */
    @GetMapping("/{postId}")
    public ResponseEntity<Page<Comment>> getComments(@PathVariable Long postId, Pageable pageable) {
        Page<Comment> comments = commentService.getCommentsByPostId(postId, pageable);
        return ResponseEntity.ok(comments);
    }
}
