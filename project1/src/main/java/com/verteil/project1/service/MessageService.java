package com.verteil.project1.service;

import com.verteil.project1.entity.Message;

import java.util.List;

public interface MessageService {

    public void saveMessage(Message message);
    public List<Message> getMessagesForUser(Long userId);
    public List<Message> getMessagesBetweenUsers(Long userId1, Long userId2);

}
