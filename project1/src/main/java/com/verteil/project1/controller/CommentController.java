package com.verteil.project1.controller;

import com.verteil.project1.entity.Comment;
import com.verteil.project1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{postId}/{userId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @PathVariable Long userId,
                                              @RequestBody Map<String, String> request) {
        String content = request.get("content");
        Comment comment = commentService.addComment(postId, userId, content);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }
}
