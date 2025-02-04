package com.verteil.project1.service;

import com.verteil.project1.entity.Post;
import com.verteil.project1.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepo postRepo;


    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);

    }
}
