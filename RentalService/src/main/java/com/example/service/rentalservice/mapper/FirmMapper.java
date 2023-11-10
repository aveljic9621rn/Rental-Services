package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.Firm;
import com.example.service.rentalservice.dto.FirmCreateDto;
import com.example.service.rentalservice.dto.FirmDto;
import com.example.service.rentalservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirmMapper {
    @Autowired
    private CityRepository cityRepository;

    public FirmDto firmToFirmDto(Firm firm) {
        FirmDto firmDto = new FirmDto();
        firmDto.setId(firm.getId());
        firmDto.setName(firm.getName());
        firmDto.setDescription(firm.getDescription());
        firmDto.setCityName(firm.getCity().getName());
        return firmDto;
    }

    public Firm firmCreateDtoToFirm(FirmCreateDto firmCreateDto) {
        Firm firm = new Firm();
        firm.setName(firmCreateDto.getName());
        firm.setDescription(firmCreateDto.getDescription());
        firm.setCity(cityRepository.findCityByName(firmCreateDto.getCityName()));
        return firm;
    }
}
