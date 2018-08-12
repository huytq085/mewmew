package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Message;
import com.culicode.dating.mewmew.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    Message save(Message message);

    List<Message> getConversation(int user1, int user2);

    List<User> getListConversation(int userId);
}
