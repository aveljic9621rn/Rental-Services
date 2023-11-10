package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.VehicleTypeCreateDto;
import com.example.service.rentalservice.dto.VehicleTypeDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.VehicleTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle_type")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<VehicleTypeDto> createVehicleType(@RequestHeader("Authorization") String authorization, @RequestBody VehicleTypeCreateDto vehicleTypeCreateDto) {
        return new ResponseEntity<>(vehicleTypeService.createVehicleType(vehicleTypeCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<VehicleTypeDto> updateVehicleType(@RequestHeader("Authorization") String authorization, @RequestBody VehicleTypeDto vehicleTypeDto) {
        return new ResponseEntity<>(vehicleTypeService.updateVehicleType(vehicleTypeDto), HttpStatus.OK);
    }
}
