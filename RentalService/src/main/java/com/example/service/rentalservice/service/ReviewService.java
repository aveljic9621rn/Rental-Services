package com.example.service.rentalservice.service;

import com.example.service.rentalservice.dto.ReviewCreateDto;
import com.example.service.rentalservice.dto.ReviewDto;

public interface ReviewService {
    ReviewDto findReview(Long id);

    ReviewDto createReview(ReviewCreateDto reviewCreateDto);

    ReviewDto updateReview(ReviewDto reviewDto);

    void deleteReview(Long id);
}
