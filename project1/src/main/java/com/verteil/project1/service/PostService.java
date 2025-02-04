package com.verteil.project1.service;

import com.verteil.project1.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<Post> getAllPosts();
    public Optional<Post> getPostById(Long id);
    public Post savePost(Post post);
    public void deletePost(Long id);
}
