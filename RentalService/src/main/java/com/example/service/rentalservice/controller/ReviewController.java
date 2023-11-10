package com.example.service.rentalservice.controller;

import com.example.service.rentalservice.dto.ReviewCreateDto;
import com.example.service.rentalservice.dto.ReviewDto;
import com.example.service.rentalservice.security.CheckSecurity;
import com.example.service.rentalservice.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<ReviewDto> createReview(@RequestHeader("Authorization") String authorization, @RequestBody ReviewCreateDto reviewCreateDto) {
        return new ResponseEntity<>(reviewService.createReview(reviewCreateDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<ReviewDto> updateReview(@RequestHeader("Authorization") String authorization, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(reviewDto), HttpStatus.OK);
    }
}
