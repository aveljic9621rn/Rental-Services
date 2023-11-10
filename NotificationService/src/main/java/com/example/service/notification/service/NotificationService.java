package com.example.service.notification.service;

import com.example.service.notification.dto.CreateNotificationDto;
import com.example.service.notification.dto.NotificationDto;
import com.example.service.notification.dto.SendNotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    NotificationDto dodajNotifikaciju(CreateNotificationDto createNotificationDto);

    Page<NotificationDto> nadjiSveNotifikacije(Pageable pageable);

    NotificationDto azurirajNotifikacije(NotificationDto notificationDto);

    NotificationDto posaljiMail(SendNotificationDto sendNotificationDto);

    Page<NotificationDto> nadjiSveNotifikacijePoUserIdu(Pageable pageable, String userId);

    Page<NotificationDto> nadjiSveNotifikacijePoUserIduIDatumu(Pageable pageable, String userId, String date);

    Page<NotificationDto> nadjiSveNotifikacijePoUserIduINotificationTYpu(Pageable pageable, String userId, String notificationType);
    void deleteById(String id);

}
