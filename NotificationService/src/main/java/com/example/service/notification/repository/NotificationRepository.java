package com.example.service.notification.repository;

import com.example.service.notification.domain.Notification;
import com.example.service.notification.domain.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUser(Pageable pageable, int userId);

    void deleteByNotificationId(int id);
    Notification findByNotificationId(int notificationId);

    Page<Notification> findByUserAndNotificationType(Pageable pageable, int notificationID, String notificationType);

    Page<Notification> findByUserAndDateBefore(Pageable pageable, int notificationID, String date);

}
