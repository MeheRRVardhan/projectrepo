package com.verteil.project1.service;

import com.verteil.project1.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Long postId, Long userId, String content);  // Added userId
    List<Comment> getCommentsByPost(Long postId);
}



