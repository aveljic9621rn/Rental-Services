package com.example.service.rentalservice.service.impl;

import com.example.service.rentalservice.domain.VehicleType;
import com.example.service.rentalservice.dto.VehicleTypeCreateDto;
import com.example.service.rentalservice.dto.VehicleTypeDto;
import com.example.service.rentalservice.mapper.VehicleTypeMapper;
import com.example.service.rentalservice.repository.VehicleTypeRepository;
import com.example.service.rentalservice.service.VehicleTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeMapper vehicleTypeMapper;
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeMapper vehicleTypeMapper, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeMapper = vehicleTypeMapper;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public VehicleTypeDto findVehicleType(Long id) {
        return null;
    }

    @Override
    public VehicleTypeDto createVehicleType(VehicleTypeCreateDto vehicleTypeCreateDto) {
        VehicleType vehicleType = vehicleTypeMapper.vehicleTypeCreateDtoToVehicleType(vehicleTypeCreateDto);
        vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleType);
    }

    @Override
    public VehicleTypeDto updateVehicleType(VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = vehicleTypeRepository.getOne(vehicleTypeDto.getId());
        if (vehicleTypeDto.getName() != null) {
            vehicleType.setName(vehicleTypeDto.getName());
        }

        vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleType);
    }

    @Override
    @Transactional
    public void deleteVehicleType(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}
