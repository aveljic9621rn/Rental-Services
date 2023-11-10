package com.example.service.rentalservice.service.impl;

import com.example.service.rentalservice.domain.Review;
import com.example.service.rentalservice.dto.ReviewCreateDto;
import com.example.service.rentalservice.dto.ReviewDto;
import com.example.service.rentalservice.mapper.ReviewMapper;
import com.example.service.rentalservice.repository.ReviewRepository;
import com.example.service.rentalservice.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewMapper reviewMapper, ReviewRepository reviewRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto findReview(Long id) {
        return null;
    }

    @Override
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto) {
        Review review = reviewMapper.reviewCreateDtoToReview(reviewCreateDto);
        reviewRepository.save(review);
        return reviewMapper.reviewToReviewDto(review);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review = reviewRepository.getOne(reviewDto.getId());
        if (reviewDto.getComment() != null) {
            review.setComment(reviewDto.getComment());
        }
        if (reviewDto.getGrade() != null) {
            review.setGrade(reviewDto.getGrade());
        }

        reviewRepository.save(review);
        return reviewMapper.reviewToReviewDto(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
