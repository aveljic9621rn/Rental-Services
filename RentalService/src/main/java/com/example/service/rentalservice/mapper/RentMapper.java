package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.Rent;
import com.example.service.rentalservice.dto.RentCreateDto;
import com.example.service.rentalservice.dto.RentDeleteDto;
import com.example.service.rentalservice.dto.RentDto;
import com.example.service.rentalservice.dto.RentNotificationDto;
import com.example.service.rentalservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RentMapper {
    @Autowired
    private VehicleRepository vehicleRepository;

    public RentDto rentToRentDto(Rent rent) {
        RentDto rentDto = new RentDto();
        rentDto.setId(rent.getId());
        rentDto.setVehicleModel(rent.getVehicle().getModel());
        rentDto.setDuration(rent.getDuration());
        rentDto.setStartDate(rent.getStartDate());
        rentDto.setUserId(rent.getUserId());
        return rentDto;
    }

    public Rent rentCreateDtoToRent(RentCreateDto rentCreateDto) {
        Rent rent = new Rent();
        rent.setVehicle(vehicleRepository.findVehicleByModel(rentCreateDto.getVehicleModel()));
        rent.setDuration(rent.getDuration());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        rent.setStartDate(dateTimeFormatter.format(localDateTime));
        rent.setUserId(rentCreateDto.getUserId());
        return rent;
    }

    public RentDeleteDto rentToRentDeleteDto(Rent rent) {
        RentDeleteDto rentDeleteDto = new RentDeleteDto();
        rentDeleteDto.setVehicleModel(rent.getVehicle().getModel());
        rentDeleteDto.setNotificationType("otkazano");
        return rentDeleteDto;
    }

    public RentNotificationDto rentCreateDtoToRentNotificationDto(RentCreateDto rentCreateDto) {
        RentNotificationDto rentNotificationDto = new RentNotificationDto();
        rentNotificationDto.setVehicleModel(rentCreateDto.getVehicleModel());
        rentNotificationDto.setDuration(rentCreateDto.getDuration());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        rentNotificationDto.setStartDate(dateTimeFormatter.format(localDateTime));
        rentNotificationDto.setNotificationType("rezervisano");
        return rentNotificationDto;
    }
}
