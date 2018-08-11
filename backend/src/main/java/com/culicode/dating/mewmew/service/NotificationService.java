package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<Notification> findAllByRecipientId(int recipientId);

    Notification save(Notification notification);

}
