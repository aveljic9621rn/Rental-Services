package com.example.service.userservice.service;

import com.example.service.userservice.dto.AdminDto;
import com.example.service.userservice.dto.CreateAdminDto;
import com.example.service.userservice.dto.TokenRequestDto;
import com.example.service.userservice.dto.TokenResponseDto;

import java.util.List;

public interface AdminService {

    List<AdminDto> nadjiSveAdmine();

    AdminDto nadjiAdmina(String id);

    AdminDto dodajAdmina(CreateAdminDto createAdminDto);

    AdminDto azurirajAdmina(AdminDto adminDto);

    void ukloniAdmina(String id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
