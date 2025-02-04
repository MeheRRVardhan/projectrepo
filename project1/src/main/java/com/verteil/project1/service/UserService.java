package com.verteil.project1.service;

import com.verteil.project1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public User saveUser(User user);
    public void deleteUser(Long id);
}
