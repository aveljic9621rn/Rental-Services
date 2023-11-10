package com.example.service.userservice.service;

import com.example.service.userservice.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<UserDto> nadjiSveUsere();

    boolean dalipostojiUser(String username);

    UserDto dodajUsera(CreateUserDto createUserDto);

    UserDto azurirajUsera(UserDto userDto);

    UserDto banujUsera(String id);

    UserDto unbanujUsera(String id);

    UserDto odobriUsera(String id);

    Float discount(String id,  Page<RankDto> rankovi);
    UserDto register(CreateUserDto createUserDto);

    UserDto updatedan(String id,  Page<RankDto> rankovi);

    void resetujlozinku(String id);
    String vratimail(String id);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
