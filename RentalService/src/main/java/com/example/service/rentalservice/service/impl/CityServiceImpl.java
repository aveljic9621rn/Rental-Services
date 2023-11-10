package com.example.service.rentalservice.service.impl;

import com.example.service.rentalservice.domain.City;
import com.example.service.rentalservice.dto.CityCreateDto;
import com.example.service.rentalservice.dto.CityDto;
import com.example.service.rentalservice.mapper.CityMapper;
import com.example.service.rentalservice.repository.CityRepository;
import com.example.service.rentalservice.service.CityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityMapper cityMapper;
    private final CityRepository cityRepository;

    public CityServiceImpl(CityMapper cityMapper, CityRepository cityRepository) {
        this.cityMapper = cityMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto findCity(Long id) {
        return null;
    }

    @Override
    public List<CityDto> listAll() {
        List<CityDto> cityDtos = new ArrayList<>();
        List<City> cities = cityRepository.findAll();

        for (City city : cities) {
            cityDtos.add(cityMapper.cityToCityDto(city));
        }

        return cityDtos;
    }

    @Override
    public CityDto createCity(CityCreateDto cityCreateDto) {
        City city = cityMapper.cityCreateDtoToCity(cityCreateDto);
        cityRepository.save(city);
        return cityMapper.cityToCityDto(city);
    }

    @Override
    public CityDto updateCity(CityDto cityDto) {
        City city = cityRepository.getOne(cityDto.getId());
        if (cityDto.getName() != null) {
            city.setName(cityDto.getName());
        }

        cityRepository.save(city);
        return cityMapper.cityToCityDto(city);
    }

    @Override
    @Transactional
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
