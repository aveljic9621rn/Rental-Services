package com.example.service.userservice.service;

import com.example.service.userservice.dto.*;

import java.util.List;

public interface ManagerService {
    
    List<ManagerDto> nadjiSveManagere();

    boolean dalipostojiManager(String username);

    ManagerDto dodajManagera(CreateManagerDto createManagerDto);

    ManagerDto azurirajManagera(ManagerDto managerDto);

    ManagerDto banujManagera(String id);

    ManagerDto unbanujManagera(String id);

    ManagerDto register(CreateManagerDto createManagerDto);

    ManagerDto odobriManagera(String id);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
