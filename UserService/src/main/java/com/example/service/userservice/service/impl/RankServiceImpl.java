package com.example.service.userservice.service.impl;

import com.example.service.userservice.domain.Uloga;
import com.example.service.userservice.domain.User;
import com.example.service.userservice.dto.CreateRankDto;
import com.example.service.userservice.dto.RankDto;
import com.example.service.userservice.mapper.AdminMapper;
import com.example.service.userservice.mapper.RankMapper;
import com.example.service.userservice.repository.AdminRepository;
import com.example.service.userservice.repository.RankRepository;
import com.example.service.userservice.security.service.TokenService;
import com.example.service.userservice.service.RankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    private final RankMapper rankMapper;
    private final RankRepository rankRepository;
    private final TokenService tokenService;


    public RankServiceImpl(RankMapper adminMapper, RankRepository adminRepository, TokenService tokenService) {
        this.rankMapper = adminMapper;
        this.rankRepository = adminRepository;
        this.tokenService = tokenService;
    }

    @Override
    public RankDto dodajRank(CreateRankDto createRankDto) {
        Uloga uloga = rankMapper.CreateRankDtoToRank(createRankDto);
        rankRepository.save(uloga);
        return rankMapper.rankToRankDto(uloga);
    }

    @Override
    public RankDto azurirajRank(RankDto rankDto) {
        Uloga uloga = rankRepository.findById(rankDto.getRankId());

        if (rankDto.getRankName() != null)
            uloga.setRankName(rankDto.getRankName());
        if (rankDto.getDiscount() != null)
            uloga.setDiscount(rankDto.getDiscount());
        if (rankDto.getNumberOfDays() != 0)
            uloga.setNumberOfDays(rankDto.getNumberOfDays());


        rankRepository.save(uloga);
        return rankMapper.rankToRankDto(uloga);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        rankRepository.deleteById(Integer.parseInt(id));
    }

    @Override
    public Page<RankDto> nadjiSveRankove(Pageable pageable) {
        return rankRepository.findAll(pageable)
                .map(rankMapper::rankToRankDto);
    }
}
