package com.example.service.userservice.service;

import com.example.service.userservice.dto.CreateRankDto;
import com.example.service.userservice.dto.CreateUserDto;
import com.example.service.userservice.dto.RankDto;
import com.example.service.userservice.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankService {


    RankDto dodajRank(CreateRankDto createRankDto);

    RankDto azurirajRank(RankDto rankDto);

    void deleteById(String id);

    Page<RankDto> nadjiSveRankove(Pageable pageable);
}
