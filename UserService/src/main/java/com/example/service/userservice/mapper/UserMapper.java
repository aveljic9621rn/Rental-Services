package com.example.service.userservice.mapper;

import com.example.service.userservice.domain.Manager;
import com.example.service.userservice.domain.User;
import com.example.service.userservice.dto.CreateManagerDto;
import com.example.service.userservice.dto.CreateUserDto;
import com.example.service.userservice.dto.ManagerDto;
import com.example.service.userservice.dto.UserDto;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class UserMapper {


    public UserDto userToUserDto(User user){

        UserDto userDto=new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setMail(user.getMail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());


        userDto.setPassportNumber(user.getPassportNumber());
        userDto.setTotalRentalsInDays(user.getTotalRentalsInDays());
        userDto.setZabrana(user.isZabrana());
        userDto.setOdobren(user.isOdobren());
        userDto.setRank_name(user.getRank_name());
        return userDto;
    }

    public User createUserDtoToUser(CreateUserDto createUserDto)
    {
        User user=new User();

        user.setUsername(createUserDto.getUsername());
        user.setPassword(createUserDto.getPassword());
        user.setMail(createUserDto.getMail());
        user.setPhoneNumber(createUserDto.getPhoneNumber());
        user.setDateOfBirth(createUserDto.getDateOfBirth());
        user.setName(createUserDto.getName());
        user.setSurname(createUserDto.getSurname());

        user.setPassportNumber(createUserDto.getPassportNumber());
        user.setTotalRentalsInDays(0);
        user.setZabrana(false);
        user.setOdobren(false);
        user.setRank_name("unranked");
        return user;
    }
}
