package com.example.service.userservice.mapper;

import com.example.service.userservice.domain.Admin;
import com.example.service.userservice.dto.AdminDto;
import com.example.service.userservice.dto.CreateAdminDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminMapper {

    public AdminDto adminToAdminDto(Admin admin) {

        AdminDto adminDto = new AdminDto();

        adminDto.setUsername(admin.getUsername());
        adminDto.setPassword(admin.getPassword());
        adminDto.setMail(admin.getMail());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        adminDto.setDateOfBirth(admin.getDateOfBirth());
        adminDto.setName(admin.getName());
        adminDto.setSurname(admin.getSurname());

        return adminDto;
    }

    public Admin createAdminDtoToAdmin(CreateAdminDto createAdminDto) {
        Admin admin = new Admin();

        admin.setUsername(createAdminDto.getUsername());
        admin.setPassword(createAdminDto.getPassword());
        admin.setMail(createAdminDto.getMail());
        admin.setPhoneNumber(createAdminDto.getPhoneNumber());
        admin.setDateOfBirth(createAdminDto.getDateOfBirth());
        admin.setName(createAdminDto.getName());
        admin.setSurname(createAdminDto.getSurname());

        return admin;
    }
}
