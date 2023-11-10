package com.example.service.userservice.service.impl;

import com.example.service.userservice.domain.Admin;
import com.example.service.userservice.dto.AdminDto;
import com.example.service.userservice.dto.CreateAdminDto;
import com.example.service.userservice.dto.TokenRequestDto;
import com.example.service.userservice.dto.TokenResponseDto;
import com.example.service.userservice.exception.NotFoundException;
import com.example.service.userservice.mapper.AdminMapper;
import com.example.service.userservice.repository.AdminRepository;
import com.example.service.userservice.security.service.TokenService;
import com.example.service.userservice.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    public AdminServiceImpl(AdminMapper adminMapper, AdminRepository adminRepository, TokenService tokenService) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
    }

    @Override
    public List<AdminDto> nadjiSveAdmine() {
        List<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            adminDtos.add(adminMapper.adminToAdminDto(admin));
        }
        return adminDtos;
    }

    @Override
    public AdminDto nadjiAdmina(String id) {

        return null;
    }

    @Override
    public AdminDto dodajAdmina(CreateAdminDto createAdminDto) {
        Admin admin = adminMapper.createAdminDtoToAdmin(createAdminDto);
        adminRepository.save(admin);
        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    public AdminDto azurirajAdmina(AdminDto adminDto) {
        Admin admin = adminRepository.findByAdminId(adminDto.getAdminId());

        if (adminDto.getUsername() != null)
            admin.setUsername(adminDto.getUsername());
        if (adminDto.getPassword() != null)
            admin.setPassword(adminDto.getPassword());
        if (adminDto.getMail() != null)
            admin.setMail(adminDto.getMail());
        if (adminDto.getPhoneNumber() != null)
            admin.setPhoneNumber(adminDto.getPhoneNumber());
        if (adminDto.getDateOfBirth() != null)
            admin.setDateOfBirth(adminDto.getDateOfBirth());
        if (adminDto.getName() != null)
            admin.setName(adminDto.getName());
        if (adminDto.getSurname() != null)
            admin.setSurname(adminDto.getSurname());

        adminRepository.save(admin);
        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    @Transactional
    public void ukloniAdmina(String id) {
        adminRepository.deleteByAdminId(Integer.parseInt(id));
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Admin admin = adminRepository
                .findAdminByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));

        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", admin.getAdminId());
        claims.put("role", "ROLE_ADMIN");
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
