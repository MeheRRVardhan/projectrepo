package com.verteil.project1.service;

import com.verteil.project1.entity.Message;
import com.verteil.project1.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    private MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public void saveMessage(Message message) {
        messageRepo.save(message);
    }

    @Override
    public List<Message> getMessagesForUser(Long userId) {
        return messageRepo.findByReceiverIdOrSenderId(userId, userId);
    }

    @Override
    public List<Message> getMessagesBetweenUsers(Long userId1, Long userId2) {
        return messageRepo.findMessagesBetweenUsers(userId1, userId2);
    }
}
