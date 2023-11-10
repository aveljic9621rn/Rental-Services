package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.RentCreateDto;
import com.example.service.rentalservice.dto.RentDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/create")
    //@CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<RentDto> createRent(@RequestHeader("Authorization") String authorization, @RequestBody RentCreateDto rentCreateDto) {
        return new ResponseEntity<>(rentService.createRent(rentCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<RentDto> updateRent(@RequestHeader("Authorization") String authorization, @RequestBody RentDto rentDto) {
        return new ResponseEntity<>(rentService.updateRent(rentDto), HttpStatus.OK);
    }
}
