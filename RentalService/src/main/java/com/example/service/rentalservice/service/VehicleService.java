package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.VehicleCreateDto;
import com.example.service.rentalservice.dto.VehicleDto;
import com.example.service.rentalservice.dto.VehicleListDto;

import java.text.ParseException;
import java.util.List;

public interface VehicleService {
    VehicleDto findVehicle(Long id);

    List<VehicleListDto> listAvailableVehicles(String startDate, String endDate) throws ParseException;

    VehicleDto createVehicle(VehicleCreateDto vehicleCreateDto);

    VehicleDto updateVehicle(VehicleDto vehicleDto);

    void deleteVehicle(Long id);
}
