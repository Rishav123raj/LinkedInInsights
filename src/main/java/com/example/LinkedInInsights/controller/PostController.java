package com.example.LinkedInInsights.controller;

import com.example.LinkedInInsights.model.Post;
import com.example.LinkedInInsights.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Fetches recent posts for a given LinkedIn Page ID with pagination.
     */
    @GetMapping("/{pageId}")
    public ResponseEntity<Page<Post>> getRecentPosts(@PathVariable String pageId, Pageable pageable) {
        Page<Post> posts = postService.getPostsByPageId(pageId, pageable);
        return ResponseEntity.ok(posts);
    }
}
