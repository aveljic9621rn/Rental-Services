package com.example.service.userservice.mapper;

import com.example.service.userservice.domain.Uloga;
import com.example.service.userservice.dto.CreateRankDto;
import com.example.service.userservice.dto.RankDto;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {
    public RankDto rankToRankDto(Uloga uloga) {
        RankDto rankDto = new RankDto();

        rankDto.setRankId(uloga.getRankId());
        rankDto.setRankName(uloga.getRankName());
        rankDto.setNumberOfDays(uloga.getNumberOfDays());
        rankDto.setDiscount(uloga.getDiscount());

        return rankDto;
    }

    public Uloga CreateRankDtoToRank(CreateRankDto createRankDto) {
        Uloga uloga = new Uloga();

        uloga.setRankName(createRankDto.getRankName());
        uloga.setNumberOfDays(createRankDto.getNumberOfDays());
        uloga.setDiscount(createRankDto.getDiscount());

        return uloga;
    }
}
