package com.example.service.notification.repository;

import com.example.service.notification.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType,Long> {

    NotificationType  findNotificationTypeByNotificationTypeId(int notificationTypeId);
    void deleteByNotificationTypeId(int id);

    NotificationType findByName(String Name);
}
