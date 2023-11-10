package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.VehicleType;
import com.example.service.rentalservice.dto.VehicleTypeCreateDto;
import com.example.service.rentalservice.dto.VehicleTypeDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {
    public VehicleTypeDto vehicleTypeToVehicleTypeDto(VehicleType vehicleType) {
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setId(vehicleType.getId());
        vehicleTypeDto.setName(vehicleTypeDto.getName());
        return vehicleTypeDto;
    }

    public VehicleType vehicleTypeCreateDtoToVehicleType(VehicleTypeCreateDto vehicleTypeCreateDto) {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(vehicleTypeCreateDto.getName());
        return vehicleType;
    }
}
