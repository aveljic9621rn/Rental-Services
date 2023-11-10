package com.example.service.notification.service.impl;


import com.example.service.notification.configuration.MailConfiguration;
import com.example.service.notification.domain.Notification;
import com.example.service.notification.domain.NotificationType;
import com.example.service.notification.dto.CreateNotificationDto;
import com.example.service.notification.dto.NotificationDto;
import com.example.service.notification.dto.NotificationTypeDto;
import com.example.service.notification.dto.SendNotificationDto;
import com.example.service.notification.mapper.NotificationMapper;
import com.example.service.notification.repository.NotificationRepository;
import com.example.service.notification.service.NotificationService;
import com.example.service.notification.service.NotificationTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    NotificationTypeService notificationTypeService;
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, NotificationTypeService notificationTypeService)
    {
        this.notificationRepository=notificationRepository;
        this.notificationMapper=notificationMapper;
        this.notificationTypeService=notificationTypeService;
    }
    @Override
    public NotificationDto dodajNotifikaciju(CreateNotificationDto createNotificationDto) {
        Notification notification = notificationMapper.createNotificationDtoToNotification(createNotificationDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationtoNotificationDto(notification);
    }

    @Override
    public Page<NotificationDto> nadjiSveNotifikacije(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationtoNotificationDto);
    }


    @Override
    public NotificationDto azurirajNotifikacije(NotificationDto notificationDto) {
        Notification notification = notificationRepository.findByNotificationId(notificationDto.getNotificationId());

        if (notificationDto.getData() != null)
            notification.setData(notificationDto.getData());
        if (notificationDto.getUser() != null)
            notification.setUser(notificationDto.getUser());
        if(notificationDto.getNotificationType()!=null)
            notification.setNotificationType(notificationDto.getNotificationType());

        notificationRepository.save(notification);
        return notificationMapper.notificationtoNotificationDto(notification);
    }

    @Override
    public NotificationDto posaljiMail(SendNotificationDto sendNotificationDto) {

        Notification notification=new Notification();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        notification.setDate(formatter.format(date));
        notification.setUser(sendNotificationDto.getEmail());
        try {
            if(sendNotificationDto.getNotificationType().equals("aktivacioni"))
            {
                   NotificationTypeDto notificationTypeDto=notificationTypeService.findbyname("aktivacioni");
                    notification.setNotificationType(notificationTypeDto.getName());

                   String[] nizpodataka = notificationTypeDto.getPodaci().split("@");
                   String poruka=notificationTypeDto.getParams();

                   ArrayList<String> podacilista=new ArrayList<>();
                    podacilista.add(sendNotificationDto.getName());
                    podacilista.add(sendNotificationDto.getSurname());
                    podacilista.add(sendNotificationDto.getUsername());


                    int br=0;
                   for(String podatak:nizpodataka)
                   {
                       if(podatak!="")
                       {
                           String s="@"+podatak.replace(",","");
                           String replace=podacilista.get(br);
                           poruka=poruka.replace(s,replace);br++;
                       }

                   }
                   notification.setData(poruka);
                   System.out.println(notification.getData());


                MailConfiguration mailConfiguration=new MailConfiguration();
                mailConfiguration.mailSenderhref(notification.getData(),"Loginuj se", "Autorizuj nalog", notification.getUser());
                notificationRepository.save(notification);

            }
            else if(sendNotificationDto.getNotificationType().equals("promenalozinke"))
            {
                NotificationTypeDto notificationTypeDto=notificationTypeService.findbyname("promenalozinke");
                notification.setNotificationType(notificationTypeDto.getName());

                String[] nizpodataka = notificationTypeDto.getPodaci().split("@");
                String poruka=notificationTypeDto.getParams();

                ArrayList<String> podacilista=new ArrayList<>();
                podacilista.add(sendNotificationDto.getName());
                podacilista.add(sendNotificationDto.getSurname());
                podacilista.add(sendNotificationDto.getUsername());


                int br=0;
                for(String podatak:nizpodataka)
                {
                    if(podatak!="")
                    {
                        String s="@"+podatak.replace(",","");
                        String replace=podacilista.get(br);
                        poruka=poruka.replace(s,replace);br++;
                    }

                }
                notification.setData(poruka);
                System.out.println(notification.getData());


                MailConfiguration mailConfiguration=new MailConfiguration();
                mailConfiguration.mailSenderhref(notification.getData(),"Promeni Lozinku", "Promeni Lozinku za svoj nalog", notification.getUser());
                notificationRepository.save(notification);
            }
            else if(sendNotificationDto.getNotificationType().equals("rezervisano"))
            {
                NotificationTypeDto notificationTypeDto=notificationTypeService.findbyname("rezervisano");
                notification.setNotificationType(notificationTypeDto.getName());

                String[] nizpodataka = notificationTypeDto.getPodaci().split("@");
                String poruka=notificationTypeDto.getParams();

                ArrayList<String> podacilista=new ArrayList<>();
                podacilista.add(sendNotificationDto.getModel());
                podacilista.add(sendNotificationDto.getBrojdana());
                podacilista.add(sendNotificationDto.getDatum());


                int br=0;
                for(String podatak:nizpodataka)
                {
                    if(podatak!="")
                    {
                        String s="@"+podatak.replace(",","");
                        String replace=podacilista.get(br);
                        poruka=poruka.replace(s,replace);br++;
                    }

                }
                notification.setData(poruka);
                System.out.println(notification.getData());


                MailConfiguration mailConfiguration=new MailConfiguration();
                mailConfiguration.mailSender(notification.getData(),"Obavestenje o rezervaciji", notification.getUser());
                notificationRepository.save(notification);

                Executors.newScheduledThreadPool(1).schedule(() -> {

                    posaljipodsetnik(notification,sendNotificationDto);

                    }, 10, TimeUnit.SECONDS);

            }
            else if(sendNotificationDto.getNotificationType().equals("otkazano"))
            {
                NotificationTypeDto notificationTypeDto=notificationTypeService.findbyname("otkazano");
                notification.setNotificationType(notificationTypeDto.getName());

                String[] nizpodataka = notificationTypeDto.getPodaci().split("@");
                String poruka=notificationTypeDto.getParams();

                ArrayList<String> podacilista=new ArrayList<>();
                podacilista.add(sendNotificationDto.getModel());


                int br=0;
                for(String podatak:nizpodataka)
                {
                    if(podatak!="")
                    {
                        String s="@"+podatak.replace(",","");
                        String replace=podacilista.get(br);
                        poruka=poruka.replace(s,replace);br++;
                    }

                }
                notification.setData(poruka);
                System.out.println(notification.getData());

                MailConfiguration mailConfiguration=new MailConfiguration();
                mailConfiguration.mailSender(notification.getData(),"Obavestenje o otkazivanju rezervacije", notification.getUser());
                notificationRepository.save(notification);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<NotificationDto> nadjiSveNotifikacijePoUserIdu(Pageable pageable,String userId) {

        return notificationRepository.findByUser(pageable, Integer.parseInt(userId))
               .map(notificationMapper::notificationtoNotificationDto);
    }

    @Override
    public Page<NotificationDto> nadjiSveNotifikacijePoUserIduIDatumu(Pageable pageable, String userId, String date) {
        return notificationRepository.findByUserAndDateBefore(pageable, Integer.parseInt(userId),date)
                .map(notificationMapper::notificationtoNotificationDto);
    }

    @Override
    public Page<NotificationDto> nadjiSveNotifikacijePoUserIduINotificationTYpu(Pageable pageable, String userId, String notificationType) {
        return notificationRepository.findByUserAndNotificationType(pageable, Integer.parseInt(userId),notificationType)
                .map(notificationMapper::notificationtoNotificationDto);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        notificationRepository.deleteByNotificationId(Integer.parseInt(id));
    }


    public void posaljipodsetnik(Notification notification,SendNotificationDto sendNotificationDto)
    {
        NotificationTypeDto notificationTypeDto=notificationTypeService.findbyname("podsetnik");
        notification.setNotificationType(notificationTypeDto.getName());

        String[] nizpodataka = notificationTypeDto.getPodaci().split("@");
        String poruka=notificationTypeDto.getParams();

        ArrayList<String> podacilista=new ArrayList<>();
        podacilista.add(sendNotificationDto.getModel());
        podacilista.add(sendNotificationDto.getBrojdana());
        podacilista.add(sendNotificationDto.getDatum());


        int br=0;
        for(String podatak:nizpodataka)
        {
            if(podatak!="")
            {
                String s="@"+podatak.replace(",","");
                String replace=podacilista.get(br);
                poruka=poruka.replace(s,replace);br++;
            }

        }
        notification.setData(poruka);
        System.out.println(notification.getData());

        MailConfiguration mailConfiguration=new MailConfiguration();
        mailConfiguration.mailSender(notification.getData(),"Podsetnik o rezervaciji", notification.getUser());
        notificationRepository.save(notification);
    }

}
