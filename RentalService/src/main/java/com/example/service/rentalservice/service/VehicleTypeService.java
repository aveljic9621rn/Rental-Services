package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.VehicleTypeCreateDto;
import com.example.service.rentalservice.dto.VehicleTypeDto;

public interface VehicleTypeService {
    VehicleTypeDto findVehicleType(Long id);

    VehicleTypeDto createVehicleType(VehicleTypeCreateDto vehicleTypeCreateDto);

    VehicleTypeDto updateVehicleType(VehicleTypeDto vehicleTypeDto);

    void deleteVehicleType(Long id);
}
