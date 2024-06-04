package com.isep.acme.reviews.dto;

import com.isep.acme.reviews.model.Review;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ReviewMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static ReviewDTO toDTO(Review review) {
        return mapper.map(review, ReviewDTO.class);
    }

    public static List<ReviewDTO> toDtoList(List<Review> reviews) {
        return reviews
                .stream()
                .map((review) -> mapper.map(review, ReviewDTO.class))
                .toList();
    }

}
