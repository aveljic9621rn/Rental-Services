package com.example.service.userservice.service.impl;

import com.example.service.userservice.domain.User;
import com.example.service.userservice.dto.*;
import com.example.service.userservice.exception.NotFoundException;
import com.example.service.userservice.listener.helper.MessageHelper;
import com.example.service.userservice.mapper.UserMapper;
import com.example.service.userservice.repository.UserRepository;
import com.example.service.userservice.security.service.TokenService;
import com.example.service.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final MessageHelper messageHelper;
    private final JmsTemplate jmsTemplate;
    private final String userDestination;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, TokenService tokenService, MessageHelper messageHelper, JmsTemplate jmsTemplate, @Value("${destination.sendNotification}") String userDestination) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.messageHelper = messageHelper;
        this.jmsTemplate = jmsTemplate;
        this.userDestination = userDestination;
    }

    @Override
    public List<UserDto> nadjiSveUsere() {
        return null;
    }

    @Override
    public boolean dalipostojiUser(String username) {

        Optional<User> users = userRepository.findUserByUsername(username);

        if(users==null)
            return false;
        else
            return true;
    }

    @Override
    public UserDto dodajUsera(CreateUserDto createUserDto) {
        User user = userMapper.createUserDtoToUser(createUserDto);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto azurirajUsera(UserDto userDto) {
        User user = userRepository.findByUserId(userDto.getUserId());

        if (userDto.getUsername() != null)
            user.setUsername(userDto.getUsername());
        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());
        if (userDto.getMail() != null)
            user.setMail(userDto.getMail());
        if (userDto.getPhoneNumber() != null)
            user.setPhoneNumber(userDto.getPhoneNumber());
        if (userDto.getDateOfBirth() != null)
            user.setDateOfBirth(userDto.getDateOfBirth());
        if (userDto.getName() != null)
            user.setName(userDto.getName());
        if (userDto.getSurname() != null)
            user.setSurname(userDto.getSurname());
        if (userDto.getPassportNumber() != null)
            user.setPassportNumber(userDto.getPassportNumber());

        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto banujUsera(String id) {
        User user = userRepository.findByUserId(Integer.parseInt(id));
        user.setZabrana(true);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto unbanujUsera(String id) {
        User user = userRepository.findByUserId(Integer.parseInt(id));
        user.setZabrana(false);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto odobriUsera(String id) {
        User user = userRepository.findByUserId(Integer.parseInt(id));
        user.setOdobren(true);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public Float discount(String id, Page<RankDto> rankovi) {
        User user = userRepository.findByUserId(Integer.parseInt(id));
        user.setTotalRentalsInDays(user.getTotalRentalsInDays()+1);
        ArrayList<RankDto> r=new ArrayList<>();
        for(RankDto rank: rankovi) {
            r.add(rank);
        }
        for(RankDto rank: r) {
            if(rank.getRankName()==rank.getRankName())
            {
                return Float.parseFloat(rank.getDiscount().replace("%",""));

            }

        }

        return (float) 0;
    }

    @Override
    public UserDto register(CreateUserDto createUserDto) {

        Optional<User> users = userRepository.findUserByUsername(createUserDto.getUsername());

        if(!users.isPresent())
        {
            User user = userMapper.createUserDtoToUser(createUserDto);
            userRepository.save(user);
            System.out.println("Dodadto");
            UserDto userDto=userMapper.userToUserDto(user);
            SendNotificationDto sendNotificationDto=new SendNotificationDto();
            sendNotificationDto.setName(user.getName());
            sendNotificationDto.setSurname(user.getSurname());
            sendNotificationDto.setUsername(user.getUsername());
            sendNotificationDto.setEmail(user.getMail());
            sendNotificationDto.setNotificationType("aktivacioni");
            jmsTemplate.convertAndSend(userDestination, messageHelper.createTextMessage(sendNotificationDto)); //salje za odobrenje accounta
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto updatedan(String id, Page<RankDto> rankovi) {

        User user = userRepository.findByUserId(Integer.parseInt(id));
        user.setTotalRentalsInDays(user.getTotalRentalsInDays()+1);
        int trenutnostanje=user.getTotalRentalsInDays()+1;
        ArrayList<RankDto> r=new ArrayList<>();
        for(RankDto rank: rankovi) {
            r.add(rank);
        }
        r.sort(Comparator.comparing(RankDto::getNumberOfDays));

        for(RankDto rank: r) {
            System.out.println(rank.getRankName());
            if(trenutnostanje>rank.getNumberOfDays())
            {
                user.setRank_name(rank.getRankName());
            }
        }
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void resetujlozinku(String id) {
        User user = userRepository.findByUserId(Integer.parseInt(id));

        if(user!=null)
        {
            SendNotificationDto sendNotificationDto=new SendNotificationDto();
            sendNotificationDto.setName(user.getName());
            sendNotificationDto.setSurname(user.getSurname());
            sendNotificationDto.setUsername(user.getUsername());
            sendNotificationDto.setEmail(user.getMail());
            sendNotificationDto.setNotificationType("promenalozinke");
            jmsTemplate.convertAndSend(userDestination, messageHelper.createTextMessage(sendNotificationDto)); //salje za odobrenje accounta

        }

    }

    @Override
    public String vratimail(String id) {

        User user = userRepository.findByUserId(Integer.parseInt(id));
        return user.getMail();
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        User user = userRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));

        if (user.isOdobren() && !user.isZabrana()) {
            //Create token payload
            Claims claims = Jwts.claims();
            claims.put("id", user.getUserId());
            claims.put("role", "ROLE_USER");
            //Generate token
            return new TokenResponseDto(tokenService.generate(claims));
        }
        return null;
    }
}
