package com.verteil.project1.service;

import com.verteil.project1.entity.Post;
import com.verteil.project1.entity.User;
import com.verteil.project1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
    @Override
    public User saveUser(User user) {
        for (Post post : user.getPosts()) {
            post.setUser(user);
        }
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}



