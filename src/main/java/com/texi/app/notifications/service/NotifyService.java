package com.texi.app.notifications.service;

import com.texi.app.domain.Notification;

import java.util.List;

public interface NotifyService {

    List<Notification> getNotificationsForUser(Long id);

}
