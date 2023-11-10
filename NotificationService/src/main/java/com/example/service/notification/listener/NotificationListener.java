package com.example.service.notification.listener;

import com.example.service.notification.dto.SendNotificationDto;
import com.example.service.notification.service.NotificationService;
import com.example.service.notification.service.impl.NotificationServiceImpl;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;


@Component
public class NotificationListener {

    private MessageHelper messageHelper;
    private NotificationServiceImpl notificationService;
    public NotificationListener( MessageHelper messageHelper, NotificationServiceImpl notificationService) {
        this.notificationService= notificationService;
        this.messageHelper = messageHelper;
    }

    @JmsListener(destination = "${destination.sendNotification}", concurrency = "5-10")
    public void sendNotification(Message message) throws JMSException {

        SendNotificationDto sendNotificationDto = messageHelper.getMessage(message, SendNotificationDto.class);

        notificationService.posaljiMail(sendNotificationDto);
    }
}
