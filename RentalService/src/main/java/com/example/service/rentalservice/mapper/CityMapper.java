package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.City;
import com.example.service.rentalservice.dto.CityCreateDto;
import com.example.service.rentalservice.dto.CityDto;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityDto cityToCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    }

    public City cityCreateDtoToCity(CityCreateDto cityCreateDto) {
        City city = new City();
        city.setName(cityCreateDto.getName());
        return city;
    }
}
