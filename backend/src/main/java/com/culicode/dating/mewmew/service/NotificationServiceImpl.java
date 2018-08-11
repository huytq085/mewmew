package com.culicode.dating.mewmew.service;

import com.culicode.dating.mewmew.domain.Notification;
import com.culicode.dating.mewmew.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notification> findAllByRecipientId(int recipientId) {
        return notificationRepository.findAllByRecipientId(recipientId);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

}
