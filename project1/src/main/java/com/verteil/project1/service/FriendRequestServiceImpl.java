package com.verteil.project1.service;
import com.verteil.project1.entity.FriendRequest;
import com.verteil.project1.repo.FriendRequestRepo;
import com.verteil.project1.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private FriendRequestRepo friendRequestRepo;

    @Override
    public FriendRequest createFriendRequest(FriendRequest friendRequest) {
        return friendRequestRepo.save(friendRequest);
    }

    @Override
    public FriendRequest acceptFriendRequest(long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepo.findById(id);
        if (friendRequest.isPresent()) {
            FriendRequest request = friendRequest.get();
            request.setAccepted(true);
            return friendRequestRepo.save(request);
        }
        return null;
    }

    @Override
    public FriendRequest rejectFriendRequest(long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepo.findById(id);
        if (friendRequest.isPresent()) {
            FriendRequest request = friendRequest.get();
            friendRequestRepo.delete(request);
            return request;
        }
        return null;
    }

    @Override
    public List<FriendRequest> getReceivedFriendRequests(long userId) {
        return friendRequestRepo.findByReceiverId(userId);
    }

    @Override
    public List<FriendRequest> getSentFriendRequests(long userId) {
        return friendRequestRepo.findBySenderId(userId);
    }

    @Override
    public void deleteFriendRequest(long id) {
        Optional<FriendRequest> friendRequest = friendRequestRepo.findById(id);
        friendRequest.ifPresent(friendRequestRepo::delete);
    }
}
