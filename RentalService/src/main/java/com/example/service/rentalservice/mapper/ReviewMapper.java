package com.example.service.rentalservice.mapper;

import com.example.service.rentalservice.domain.Review;
import com.example.service.rentalservice.dto.ReviewCreateDto;
import com.example.service.rentalservice.dto.ReviewDto;
import com.example.service.rentalservice.repository.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    @Autowired
    private FirmRepository firmRepository;

    public ReviewDto reviewToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setComment(review.getComment());
        reviewDto.setGrade(review.getGrade());
        reviewDto.setFirmName(review.getFirm().getName());
        return reviewDto;
    }

    public Review reviewCreateDtoToReview(ReviewCreateDto reviewCreateDto) {
        Review review = new Review();
        review.setGrade(reviewCreateDto.getGrade());
        review.setComment(reviewCreateDto.getComment());
        review.setFirm(firmRepository.findFirmByName(reviewCreateDto.getFirmName()));
        return review;
    }
}
