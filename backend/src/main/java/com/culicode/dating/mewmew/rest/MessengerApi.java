package com.culicode.dating.mewmew.rest;


import com.culicode.dating.mewmew.domain.Message;
import com.culicode.dating.mewmew.domain.Notification;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class MessengerApi {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/messenger_send")
    public void notifyFriendRequest(Message message){
        System.out.println("messenger ne request");
        System.out.println(JsonUtil.encode(message));
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getRecipientId()),
                "/queue/messenger",
                message
        );

    }
}
