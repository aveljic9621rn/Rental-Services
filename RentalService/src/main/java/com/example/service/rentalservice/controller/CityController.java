package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.CityCreateDto;
import com.example.service.rentalservice.dto.CityDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<CityDto> createCity(@RequestHeader("Authorization") String authorization, @RequestBody CityCreateDto cityCreateDto) {
        return new ResponseEntity<>(cityService.createCity(cityCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<CityDto> updateCity(@RequestHeader("Authorization") String authorization, @RequestBody CityDto cityDto) {
        return new ResponseEntity<>(cityService.updateCity(cityDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<List<CityDto>> listCities(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(cityService.listAll(), HttpStatus.OK);
    }
}
