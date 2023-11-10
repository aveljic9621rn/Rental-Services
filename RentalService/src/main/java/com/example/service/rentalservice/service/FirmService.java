package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.FirmCreateDto;
import com.example.service.rentalservice.dto.FirmDto;

import java.util.List;

public interface FirmService {
    FirmDto findFirm(Long id);

    List<FirmDto> listAll();

    FirmDto createFirm(FirmCreateDto firmCreateDto);

    FirmDto updateFirm(FirmDto firmDto);

    void deleteFirm(Long id);
}
