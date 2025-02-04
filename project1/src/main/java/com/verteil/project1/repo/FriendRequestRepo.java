package com.verteil.project1.repo;

import com.verteil.project1.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiverId(long userId);

    List<FriendRequest> findBySenderId(long userId);
}
