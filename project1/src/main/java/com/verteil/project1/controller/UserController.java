package com.verteil.project1.controller;

import com.verteil.project1.entity.FriendRequest;
import com.verteil.project1.entity.Message;
import com.verteil.project1.entity.Post;
import com.verteil.project1.entity.User;
import com.verteil.project1.repo.UserRepo;
import com.verteil.project1.service.MessageService;
import com.verteil.project1.service.MessageServiceImpl;
import com.verteil.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.MessageCodeFormatter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;


    @Autowired
    public UserController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user != null && user.getPosts() != null) {
            for (Post post : user.getPosts()) {
                post.setUser(user);
            }
        }

        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{senderId}/{receiverId}/sendMessage")
    public ResponseEntity<String> sendMessage(@PathVariable("senderId") Long senderId,
                                              @PathVariable("receiverId") Long receiverId,
                                              @RequestBody Message message) {

        Optional<User> sender = userService.getUserById(senderId);
        Optional<User> receiver = userService.getUserById(receiverId);

        if (sender.isEmpty() || receiver.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sender or Receiver not found.");
        }
        message.setSender(sender.get());
        message.setReceiver(receiver.get());
        messageService.saveMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully!");
    }

    @GetMapping("/{userId}/messages")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathVariable Long userId) {
        List<Message> messages = messageService.getMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{userId1}/{userId2}/messages")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        List<Message> messages = messageService.getMessagesBetweenUsers(userId1, userId2);
        return ResponseEntity.ok(messages);
    }



}

