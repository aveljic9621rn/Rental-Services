package com.example.service.userservice.service.impl;

import com.example.service.userservice.domain.Manager;
import com.example.service.userservice.domain.User;
import com.example.service.userservice.dto.*;
import com.example.service.userservice.exception.NotFoundException;
import com.example.service.userservice.listener.helper.MessageHelper;
import com.example.service.userservice.mapper.ManagerMapper;
import com.example.service.userservice.repository.ManagerRepository;
import com.example.service.userservice.security.service.TokenService;
import com.example.service.userservice.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;
    private final TokenService tokenService;
    private final MessageHelper messageHelper;
    private final JmsTemplate jmsTemplate;
    private final String managerDestination;
    //private JmsTemplate jmsTemplate;

    public ManagerServiceImpl(ManagerMapper managerMapper, ManagerRepository managerRepository,
                              TokenService tokenService, MessageHelper messageHelper, JmsTemplate jmsTemplate, @Value("${destination.createOrder}") String managerDestination) {
        this.managerMapper = managerMapper;
        this.managerRepository = managerRepository;
        this.tokenService = tokenService;
        this.messageHelper=messageHelper;
        this.jmsTemplate=jmsTemplate;
        this.managerDestination = managerDestination;
    }

    @Override
    public List<ManagerDto> nadjiSveManagere() {
        return null;
    }

    @Override
    public boolean dalipostojiManager(String username) {

        Optional<Manager> managers = managerRepository.findManagerByUsername(username);
        if(managers==null)
            return false;



        return true;
    }

    @Override
    public ManagerDto dodajManagera(CreateManagerDto createManagerDto) {
        Manager manager = managerMapper.createManagerDtoToManager(createManagerDto);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto azurirajManagera(ManagerDto managerDto) {
        Manager manager = managerRepository.findByManagerId(managerDto.getManagerId());

        if (managerDto.getUsername() != null)
            manager.setUsername(managerDto.getUsername());
        if (managerDto.getPassword() != null)
            manager.setPassword(managerDto.getPassword());
        if (managerDto.getMail() != null)
            manager.setMail(managerDto.getMail());
        if (managerDto.getPhoneNumber() != null)
            manager.setPhoneNumber(managerDto.getPhoneNumber());
        if (managerDto.getDateOfBirth() != null)
            manager.setDateOfBirth(managerDto.getDateOfBirth());
        if (managerDto.getName() != null)
            manager.setName(managerDto.getName());
        if (managerDto.getSurname() != null)
            manager.setSurname(managerDto.getSurname());
        if (managerDto.getCompanyName() != null)
            manager.setCompanyName(managerDto.getCompanyName());
        if (managerDto.getEmploymentDate() != null)
            manager.setEmploymentDate(managerDto.getEmploymentDate());

        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto banujManagera(String id) {

        Manager manager = managerRepository.findByManagerId(Integer.parseInt(id));
        manager.setZabrana(true);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto unbanujManagera(String id) {

        Manager manager = managerRepository.findByManagerId(Integer.parseInt(id));
        manager.setZabrana(false);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto odobriManagera(String id) {

        Manager manager = managerRepository.findByManagerId(Integer.parseInt(id));
        manager.setOdobren(true);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }
    @Override
    public ManagerDto register(CreateManagerDto createManagerDto) {


        Optional<Manager> managers = managerRepository.findManagerByUsername(createManagerDto.getUsername());
        if(!managers.isPresent())
        {

            Manager manager = managerMapper.createManagerDtoToManager(createManagerDto);
            managerRepository.save(manager);
            System.out.println("Dodadto");
            ManagerDto managerdto=managerMapper.managerToManagerDto(manager);
            jmsTemplate.convertAndSend(managerDestination, messageHelper.createTextMessage(managerdto)); //salje za odobrenje accounta
            return managerdto;
        }
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        try {
            Manager manager = managerRepository
                    .findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                    .orElseThrow(() -> new NotFoundException(String
                            .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                    tokenRequestDto.getPassword())));


            if(manager.isOdobren() && !manager.isZabrana())
            {
                //Create token payload
                Claims claims = Jwts.claims();
                claims.put("id", manager.getManagerId());
                claims.put("role", "ROLE_MANAGER");
                return new TokenResponseDto(tokenService.generate(claims));
            }

        } catch (Exception e) {
            return null;
        }
        return null;

    }


}
