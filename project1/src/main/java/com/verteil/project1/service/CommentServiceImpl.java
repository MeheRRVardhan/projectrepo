package com.verteil.project1.service;

import com.verteil.project1.entity.Comment;
import com.verteil.project1.entity.Post;
import com.verteil.project1.entity.User;
import com.verteil.project1.repo.CommentRepo;
import com.verteil.project1.repo.PostRepo;
import com.verteil.project1.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Comment addComment(Long postId, Long userId, String content) {
        Optional<Post> post = postRepo.findById(postId);
        Optional<User> user = userRepo.findById(userId);  // Fetch user by userId

        if (post.isPresent() && user.isPresent()) {
            Comment comment = new Comment(content, post.get(), user.get());  // Associate user with post
            return commentRepo.save(comment);
        } else {
            throw new RuntimeException("Post or User not found");
        }
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
    }
}
