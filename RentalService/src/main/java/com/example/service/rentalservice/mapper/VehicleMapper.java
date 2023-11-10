package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.Vehicle;
import com.example.service.rentalservice.dto.VehicleCreateDto;
import com.example.service.rentalservice.dto.VehicleDto;
import com.example.service.rentalservice.dto.VehicleListDto;
import com.example.service.rentalservice.repository.FirmRepository;
import com.example.service.rentalservice.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    @Autowired
    private FirmRepository firmRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setModel(vehicleDto.getModel());
        vehicleDto.setPrice(vehicleDto.getPrice());
        vehicleDto.setVehicleType(vehicle.getVehicleType().getName());
        vehicleDto.setFirmName(vehicle.getFirm().getName());
        return vehicleDto;
    }

    public Vehicle vehicleCreateDtoToVehicle(VehicleCreateDto vehicleCreateDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleCreateDto.getModel());
        vehicle.setPrice(vehicleCreateDto.getPrice());
        vehicle.setVehicleType(vehicleTypeRepository.findVehicleTypeByName(vehicleCreateDto.getVehicleType()));
        vehicle.setFirm(firmRepository.findFirmByName(vehicleCreateDto.getFirmName()));
        return vehicle;
    }

    public VehicleListDto vehicleToVehicleListDto(Vehicle vehicle) {
        VehicleListDto vehicleListDto = new VehicleListDto();
        vehicleListDto.setVehicleType(vehicle.getVehicleType().getName());
        vehicleListDto.setModel(vehicle.getModel());
        vehicleListDto.setPrice(vehicle.getPrice());
        vehicleListDto.setFirmName(vehicle.getFirm().getName());
        vehicleListDto.setCityName(vehicle.getFirm().getCity().getName());
        return vehicleListDto;
    }
}
