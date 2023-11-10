package com.example.service.notification.mapper;


import com.example.service.notification.domain.Notification;
import com.example.service.notification.dto.CreateNotificationDto;
import com.example.service.notification.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class NotificationMapper {
    public NotificationDto notificationtoNotificationDto(Notification notification)
    {
        NotificationDto notificationDto=new NotificationDto();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        notificationDto.setNotificationId(notification.getNotificationId());
        notificationDto.setData(notification.getData());
        notificationDto.setUser(notification.getUser());
        notificationDto.setNotificationType(notification.getNotificationType());
        notificationDto.setManagers(notification.getManagers());
        notificationDto.setData(formatter.format(date));
        return notificationDto;
    }

    public Notification createNotificationDtoToNotification(CreateNotificationDto createNotificationDto)
    {
        Notification notification=new Notification();

        notification.setData(createNotificationDto.getData());
        notification.setUser(createNotificationDto.getUser());
        notification.setNotificationType(createNotificationDto.getNotificationType());
        notification.setManagers(createNotificationDto.getManagers());
        notification.setDate(createNotificationDto.getData());
        return notification;
    }

}
