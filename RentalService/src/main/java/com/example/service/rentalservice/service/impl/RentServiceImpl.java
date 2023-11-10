package com.example.service.rentalservice.service.impl;

import com.example.service.rentalservice.domain.Rent;
import com.example.service.rentalservice.dto.RentCreateDto;
import com.example.service.rentalservice.dto.RentDeleteDto;
import com.example.service.rentalservice.dto.RentDto;
import com.example.service.rentalservice.dto.RentNotificationDto;
import com.example.service.rentalservice.listener.helper.MessageHelper;
import com.example.service.rentalservice.mapper.RentMapper;
import com.example.service.rentalservice.repository.RentRepository;
import com.example.service.rentalservice.service.RentService;
import io.github.resilience4j.retry.Retry;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class RentServiceImpl implements RentService {
    private final RentMapper rentMapper;
    private final RentRepository rentRepository;
    private final RestTemplate userServiceRestTemplate;
    private final MessageHelper messageHelper;
    private final JmsTemplate jmsTemplate;
    private final String createRentDestination;
    private final String deleteRentDestination;
    private final Retry userServiceRetry;

    public RentServiceImpl(RentMapper rentMapper, RentRepository rentRepository, RestTemplate userServiceRestTemplate, JmsTemplate jmsTemplate, MessageHelper messageHelper, @Value("${destination.createRent}") String createRentDestination, @Value("${destination.deleteRent}") String deleteRentDestination, Retry userServiceRetry) {
        this.rentMapper = rentMapper;
        this.rentRepository = rentRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.createRentDestination = createRentDestination;
        this.deleteRentDestination = deleteRentDestination;
        this.userServiceRetry = userServiceRetry;
    }

    @Override
    public RentDto findRent(Long id) {
        return null;
    }

    @Override
    public RentDto createRent(RentCreateDto rentCreateDto) {
        Rent rent = rentMapper.rentCreateDtoToRent(rentCreateDto);
        float discount = Retry.decorateSupplier(userServiceRetry, () -> {
            try {
                return getDiscount(rentCreateDto.getUserId());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }).get();
        rent.setDiscount((int) discount);
        String email = Retry.decorateSupplier(userServiceRetry, () -> {
            try {
                return getEmail(rentCreateDto.getUserId());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }).get();
        RentNotificationDto rentNotificationDto = rentMapper.rentCreateDtoToRentNotificationDto(rentCreateDto);
        rentNotificationDto.setUserEmail(email);

        rentRepository.save(rent);
        jmsTemplate.convertAndSend(createRentDestination, messageHelper.createTextMessage(rentNotificationDto));
        return rentMapper.rentToRentDto(rent);
    }

    @Override
    public RentDto updateRent(RentDto rentDto) {
        Rent rent = rentRepository.getOne(rentDto.getId());
        if (rentDto.getDuration() != null) {
            rent.setDuration(rentDto.getDuration());
        }
        if (rentDto.getStartDate() != null) {
            rent.setStartDate(rentDto.getStartDate());
        }

        rentRepository.save(rent);
        return rentMapper.rentToRentDto(rent);
    }

    @Override
    @Transactional
    public void deleteRent(Long id) {
        Rent rent = rentRepository.getOne(id);
        String email = Retry.decorateSupplier(userServiceRetry, () -> {
            try {
                return getEmail(rent.getUserId());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }).get();
        RentDeleteDto rentDeleteDto = rentMapper.rentToRentDeleteDto(rent);
        rentDeleteDto.setUserEmail(email);
        jmsTemplate.convertAndSend(deleteRentDestination, messageHelper.createTextMessage(rentDeleteDto));
        rentRepository.deleteById(id);
    }

    private Float getDiscount(Long userId) throws NotFoundException {
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting discount for user id: " + userId);
        try {
            return userServiceRestTemplate.exchange("/user/discount/" + userId, HttpMethod.GET, null, Float.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", userId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    private String getEmail(Long userId) throws NotFoundException {
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting discount for user id: " + userId);
        try {
            return userServiceRestTemplate.exchange("/user/email/" + userId, HttpMethod.GET, null, String.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", userId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }
}
