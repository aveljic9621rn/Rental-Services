package com.example.service.notification.controller;

import com.example.service.notification.dto.*;
import com.example.service.notification.service.NotificationService;
import com.example.service.notification.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {
    NotificationService notificationService;
    NotificationTypeService notificationTypeService;
    public Controller(NotificationService notificationService, NotificationTypeService notificationTypeService)
    {
        this.notificationService=notificationService;
        this.notificationTypeService=notificationTypeService;
    }


    //NOTIFIKACIJE
    @PostMapping("/notification/add")
    public ResponseEntity<NotificationDto> dodajNotifikaciju(@RequestBody CreateNotificationDto createNotificationDto) {
        return new ResponseEntity<>(notificationService.dodajNotifikaciju(createNotificationDto), HttpStatus.OK);
    }

    @PostMapping("/notification/findall")
    public ResponseEntity<Page<NotificationDto>> nadjiSveNotifikacije(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(notificationService.nadjiSveNotifikacije(pageable), HttpStatus.OK);
    }

    @PostMapping("/notification/update")
    public ResponseEntity<NotificationDto> azurirajNotifikacije(@RequestBody NotificationDto notificationDto) {
        return new ResponseEntity<>(notificationService.azurirajNotifikacije(notificationDto), HttpStatus.OK);
    }

    @PostMapping("/notification/delete")
    public ResponseEntity<NotificationDto> deleteNotifikaciju(@RequestBody String Json) {
        notificationService.deleteById(Json);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/notification/findallbyuser")
    public ResponseEntity<Page<NotificationDto>> nadjiSveNotifikacijePoUserIdu(@ApiIgnore Pageable pageable,@RequestBody String Json) {
        return new ResponseEntity<>(notificationService.nadjiSveNotifikacijePoUserIdu(pageable,Json), HttpStatus.OK);
    }

    @PostMapping("/notification/findallbydate")
    public ResponseEntity<Page<NotificationDto>> nadjiSveNotifikacijePoUserIduIVremenu(@ApiIgnore Pageable pageable,@RequestBody String Json) {
        String[] s=Json.split(",");
        return new ResponseEntity<>(notificationService.nadjiSveNotifikacijePoUserIduIDatumu(pageable,s[0],s[1]), HttpStatus.OK);
    }

    @PostMapping("/notification/findallbynotificationtype")
    public ResponseEntity<Page<NotificationDto>> nadjiSveNotifikacijePoUserIduINotifikacijaTypu(@ApiIgnore Pageable pageable,@RequestBody String Json) {
        String[] s=Json.split(",");
        return new ResponseEntity<>(notificationService.nadjiSveNotifikacijePoUserIduINotificationTYpu(pageable,s[0],s[1]), HttpStatus.OK);
    }
    //NOTIFIKACIJE TIP

    @PostMapping("/notificationtype/add")
    public ResponseEntity<NotificationTypeDto> dodajNotificationType(@RequestBody CreateNotificationTypeDto createNotificationDto) {
        return new ResponseEntity<>(notificationTypeService.dodajNotificationType(createNotificationDto), HttpStatus.OK);
    }

    @PostMapping("/notificationtype/findall")
    public ResponseEntity<Page<NotificationTypeDto>> nadjiSveNotifikacationTYpe(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(notificationTypeService.nadjiSveNotifikacationTYpe(pageable), HttpStatus.OK);
    }


    @PostMapping("/notificationtype/update")
    public ResponseEntity<NotificationTypeDto> azurirajNotificationTYpe(@RequestBody NotificationTypeDto notificationTypeDto) {
        return new ResponseEntity<>(notificationTypeService.azurirajNotificationTYpe(notificationTypeDto), HttpStatus.OK);
    }

    @PostMapping("/notificationtype/delete")
    public ResponseEntity<NotificationTypeDto> deleteNotificationTYpe(@RequestBody String Json) {
        notificationTypeService.deleteById(Json);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //SLANJE MAILOVA SINHRONO
    @PostMapping("/send")
    public ResponseEntity<NotificationTypeDto> send(@RequestBody SendNotificationDto sendNotificationDto) {

        notificationService.posaljiMail(sendNotificationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
