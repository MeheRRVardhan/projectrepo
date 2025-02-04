package com.verteil.project1.controller;

import com.verteil.project1.entity.FriendRequest;
import com.verteil.project1.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping
    public FriendRequest sendFriendRequest(@RequestBody FriendRequest friendRequest) {
        return friendRequestService.createFriendRequest(friendRequest);
    }

    @PutMapping("/accept/{id}")
    public FriendRequest acceptFriendRequest(@PathVariable long id) {
        return friendRequestService.acceptFriendRequest(id);
    }

    @PutMapping("/reject/{id}")
    public FriendRequest rejectFriendRequest(@PathVariable long id) {
        return friendRequestService.rejectFriendRequest(id);
    }

    @GetMapping("/received/{userId}")
    public List<FriendRequest> getReceivedFriendRequests(@PathVariable long userId) {
        return friendRequestService.getReceivedFriendRequests(userId);
    }

    @GetMapping("/sent/{userId}")
    public List<FriendRequest> getSentFriendRequests(@PathVariable long userId) {
        return friendRequestService.getSentFriendRequests(userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFriendRequest(@PathVariable long id) {
        friendRequestService.deleteFriendRequest(id);
    }
}
