package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.Notification;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationApi {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public String chat(String body){
        return body;
    }

    @MessageMapping("/friend_request")
//    @SendTo("/topic/notification")
    public void notify(Notification notification){
        System.out.println("notiy ne");
        System.out.println(JsonUtil.encode(notification));
        messagingTemplate.convertAndSendToUser(
                notification.getUsername(),
                "/queue/notify",
                notification
        );

    }

}
