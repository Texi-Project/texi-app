package com.texi.app.notifications.repository;

import com.texi.app.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /*
     * select t.* from user u
     *     join user_notifications n on u.id = n.target_id
     *     join notification t on n.notifications_id = t.id
     *     where u.id = :id;
     */
    @Query("select t from User u join u.notifications n join n.target t where u.id = :id")
    List<Notification> getAllNotifications(Long id);

    /*
     * select n.* from notification n
     *                     join user_notifications t on n.id = t.notifications_id
     *                     join user u on t.target_id = u.id
     * where u.id = 5;
     */
    @Query("select n from Notification n join n.target u where u.id = :id")
    List<Notification> getNotifications(Long id);
}
