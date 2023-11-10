package com.example.service.userservice.mapper;

import com.example.service.userservice.domain.Manager;
import com.example.service.userservice.dto.CreateManagerDto;
import com.example.service.userservice.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ManagerMapper {
    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();

        managerDto.setUsername(manager.getUsername());
        managerDto.setPassword(manager.getPassword());
        managerDto.setMail(manager.getMail());
        managerDto.setPhoneNumber(manager.getPhoneNumber());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setName(manager.getName());
        managerDto.setSurname(manager.getSurname());

        managerDto.setCompanyName(manager.getCompanyName());
        managerDto.setEmploymentDate(manager.getEmploymentDate());
        managerDto.setZabrana(manager.isZabrana());
        managerDto.setOdobren(manager.isOdobren());
        return managerDto;
    }

    public Manager createManagerDtoToManager(CreateManagerDto createManagerDto) {
        Manager manager = new Manager();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        manager.setUsername(createManagerDto.getUsername());
        manager.setPassword(createManagerDto.getPassword());
        manager.setMail(createManagerDto.getMail());
        manager.setPhoneNumber(createManagerDto.getPhoneNumber());
        manager.setDateOfBirth(createManagerDto.getDateOfBirth());
        manager.setName(createManagerDto.getName());
        manager.setSurname(createManagerDto.getSurname());

        manager.setCompanyName(createManagerDto.getCompanyName());
        manager.setEmploymentDate(formatter.format(date));
        manager.setZabrana(false);
        manager.setOdobren(false);
        return manager;
    }
}
