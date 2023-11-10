package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.VehicleCreateDto;
import com.example.service.rentalservice.dto.VehicleDto;
import com.example.service.rentalservice.dto.VehicleListDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<VehicleDto> createVehicle(@RequestHeader("Authorization") String authorization, @RequestBody VehicleCreateDto vehicleCreateDto) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<VehicleDto> updateVehicle(@RequestHeader("Authorization") String authorization, @RequestBody VehicleDto vehicleDto) {
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleDto), HttpStatus.OK);
    }

    @GetMapping("/list_available")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<List<VehicleListDto>> listVehicles(@RequestHeader("Authorization") String authorization, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        return new ResponseEntity<>(vehicleService.listAvailableVehicles(startDate, endDate), HttpStatus.OK);
    }
}
