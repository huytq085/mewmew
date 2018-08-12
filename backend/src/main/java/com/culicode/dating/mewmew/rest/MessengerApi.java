package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Message;
import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.service.MessageService;
import com.culicode.dating.mewmew.service.UserService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class MessengerApi {

    private final String BASE_URI = "/messengers";
    private final String CONVERSATION_URI = BASE_URI + "/conversation/{user1}/{user2}";
    private final String LIST_CONVERSATION_URI = BASE_URI + "/{userId}";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(
            value = CONVERSATION_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Message> getConversation(@PathVariable int user1, @PathVariable int user2) {
        return messageService.getConversation(user1, user2);
    }

    @RequestMapping(
            value = LIST_CONVERSATION_URI,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> getListConversation(@PathVariable int userId) {
//        TODO: Lấy danh sách từ những cuộc trò chuyện
//        This is for testing purpose
        User user = userService.findById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        return userService.getFriends(user.getUsername());
    }

    @MessageMapping("/messenger_send")
    public void notifyFriendRequest(Message message) {
        System.out.println("messenger ne request");
        System.out.println(JsonUtil.encode(message));
        messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getRecipientId()),
                "/queue/messenger",
                message
        );
    }
}
