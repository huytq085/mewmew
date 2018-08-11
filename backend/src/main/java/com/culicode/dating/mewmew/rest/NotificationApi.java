package com.culicode.dating.mewmew.rest;

import com.culicode.dating.mewmew.domain.Notification;
import com.culicode.dating.mewmew.service.NotificationService;
import com.culicode.dating.mewmew.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class NotificationApi {

    private final String BASE_URI = "/notification";
    private final String GET_ALL_URL = BASE_URI + "/{recipientId}";
    private final String MARK_READ_URL = BASE_URI + "/markread";

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(
            value = GET_ALL_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Notification> getAll(@PathVariable int recipientId){
        return notificationService.findAllByRecipientId(recipientId);
    }

    @RequestMapping(
            value = MARK_READ_URL,
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Notification markRead(@RequestBody Notification notification) {
        notification.setRead(true);
        return notificationService.save(notification);
    }


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
        notificationService.save(notification);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(notification.getRecipientId()),
                "/queue/notify",
                notification
        );

    }

}
