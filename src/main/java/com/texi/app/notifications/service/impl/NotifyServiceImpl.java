package com.texi.app.notifications.service.impl;

import com.texi.app.domain.Notification;
import com.texi.app.notifications.repository.NotificationRepository;
import com.texi.app.notifications.service.NotifyService;
import com.texi.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Notification> getNotificationsForUser(Long id) {
        List<Notification> notifications = notificationRepository.getNotifications(id);
        notifications.stream().forEach(n -> n.setOwner(userRepository.findById(Long.parseLong(n.getText())).get()));
        return notifications;
    }
}
