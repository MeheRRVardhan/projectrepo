package com.verteil.project1.controller;

import com.verteil.project1.entity.Post;
import com.verteil.project1.entity.User;
import com.verteil.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/Register")
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user != null && user.getPosts() != null) {
            for (Post post : user.getPosts()) {
                post.setUser(user);
            }
        }

        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
