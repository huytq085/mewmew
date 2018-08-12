package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Message;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getConversation(int user1, int user2) {
        return messageRepository.findConversation(user1, user2);
    }

    @Override
    public List<User> getListConversation(int userId) {
        return messageRepository.findListConversation(userId);
    }

}
