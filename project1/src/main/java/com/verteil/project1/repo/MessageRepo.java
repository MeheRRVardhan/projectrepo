package com.verteil.project1.repo;

import com.verteil.project1.entity.Message;
import org.hibernate.type.ListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByReceiverIdOrSenderId(Long receiverId, Long senderId);
    @Query("SELECT m FROM Message m WHERE (m.sender.id = :userId1 AND m.receiver.id = :userId2) OR (m.sender.id = :userId2 AND m.receiver.id = :userId1)")
    List<Message> findMessagesBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);


}
