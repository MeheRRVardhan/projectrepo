package com.verteil.project1.service;

import com.verteil.project1.entity.FriendRequest;

import java.util.List;

public interface FriendRequestService {

    FriendRequest createFriendRequest(FriendRequest friendRequest);

    FriendRequest acceptFriendRequest(long id);

    FriendRequest rejectFriendRequest(long id);

    public List<FriendRequest> getReceivedFriendRequests(long userId);

    List<FriendRequest> getSentFriendRequests(long userId);

    void deleteFriendRequest(long id);
}
