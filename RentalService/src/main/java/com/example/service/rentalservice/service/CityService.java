package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.CityCreateDto;
import com.example.service.rentalservice.dto.CityDto;

import java.util.List;

public interface CityService {
    CityDto findCity(Long id);

    List<CityDto> listAll();

    CityDto createCity(CityCreateDto cityCreateDto);

    CityDto updateCity(CityDto cityDto);

    void deleteCity(Long id);
}
