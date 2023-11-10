package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.RentCreateDto;
import com.example.service.rentalservice.dto.RentDto;

public interface RentService {
    RentDto findRent(Long id);

    RentDto createRent(RentCreateDto rentCreateDto);

    RentDto updateRent(RentDto rentDto);

    void deleteRent(Long id);
}
