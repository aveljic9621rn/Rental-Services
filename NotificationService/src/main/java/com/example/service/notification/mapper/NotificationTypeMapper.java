package com.example.service.notification.mapper;

import com.example.service.notification.domain.NotificationType;
import com.example.service.notification.dto.CreateNotificationTypeDto;
import com.example.service.notification.dto.NotificationDto;
import com.example.service.notification.dto.NotificationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {




    public NotificationTypeDto notificationTypetoNotificationTypeDto(NotificationType notificationType)
    {
        NotificationTypeDto notificationTypeDto=new NotificationTypeDto();

        notificationTypeDto.setName(notificationType.getName());
        notificationTypeDto.setNotificationTypeId(notificationType.getNotificationTypeId());
        notificationTypeDto.setPodaci(notificationType.getPodaci());
        notificationTypeDto.setParams(notificationType.getParams());

        return notificationTypeDto;
    }

    public NotificationType createNotificationTypeDtoToNotificationType(CreateNotificationTypeDto createNotificationTypeDto)
    {
        NotificationType notificationType=new NotificationType();

        notificationType.setName(createNotificationTypeDto.getName());
        notificationType.setParams(createNotificationTypeDto.getParams());
        notificationType.setPodaci(createNotificationTypeDto.getPodaci());

        return notificationType;
    }

}
