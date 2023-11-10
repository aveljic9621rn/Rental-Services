package com.example.service.notification.service.impl;

import com.example.service.notification.domain.NotificationType;
import com.example.service.notification.dto.CreateNotificationTypeDto;
import com.example.service.notification.dto.NotificationTypeDto;
import com.example.service.notification.mapper.NotificationTypeMapper;
import com.example.service.notification.repository.NotificationTypeRepository;
import com.example.service.notification.service.NotificationTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class NotificationTypeServiceImpl implements NotificationTypeService {

    NotificationTypeRepository notificationTypeRepository;
    NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public NotificationTypeDto dodajNotificationType(CreateNotificationTypeDto createNotificationTypeDto) {
        NotificationType notificationType = notificationTypeMapper.createNotificationTypeDtoToNotificationType(createNotificationTypeDto);
        notificationTypeRepository.save(notificationType);
        return notificationTypeMapper.notificationTypetoNotificationTypeDto(notificationType);
    }

    @Override
    public Page<NotificationTypeDto> nadjiSveNotifikacationTYpe(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable)
                .map(notificationTypeMapper::notificationTypetoNotificationTypeDto);
    }

    @Override
    public NotificationTypeDto azurirajNotificationTYpe(NotificationTypeDto notificationTypeDto) {
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByNotificationTypeId(notificationTypeDto.getNotificationTypeId());

        if (notificationTypeDto.getName() != null)
            notificationType.setName(notificationTypeDto.getName());
        if (notificationTypeDto.getParams() != null)
            notificationType.setParams(notificationTypeDto.getParams());
        if (notificationTypeDto.getPodaci() != null)
            notificationType.setPodaci(notificationTypeDto.getPodaci());

        notificationTypeRepository.save(notificationType);
        return notificationTypeMapper.notificationTypetoNotificationTypeDto(notificationType);
    }

    @Override
    public NotificationTypeDto findbyname(String name) {
        NotificationType notificationType = notificationTypeRepository.findByName(name);
        return notificationTypeMapper.notificationTypetoNotificationTypeDto(notificationType);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        notificationTypeRepository.deleteByNotificationTypeId(Integer.parseInt(id));
    }
}
