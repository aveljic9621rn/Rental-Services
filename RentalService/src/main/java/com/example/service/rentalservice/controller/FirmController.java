package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.FirmCreateDto;
import com.example.service.rentalservice.dto.FirmDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.FirmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firm")
public class FirmController {
    private final FirmService firmService;

    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<FirmDto> createFirm(@RequestHeader("Authorization") String authorization, @RequestBody FirmCreateDto firmCreateDto) {
        return new ResponseEntity<>(firmService.createFirm(firmCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<FirmDto> updateFirm(@RequestHeader("Authorization") String authorization, @RequestBody FirmDto firmDto) {
        return new ResponseEntity<>(firmService.updateFirm(firmDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<List<FirmDto>> listVehicles(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(firmService.listAll(), HttpStatus.OK);
    }
}
