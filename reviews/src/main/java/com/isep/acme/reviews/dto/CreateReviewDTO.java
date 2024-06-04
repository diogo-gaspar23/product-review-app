package com.isep.acme.reviews.dto;

import lombok.Data;

@Data
public class CreateReviewDTO {
    private String reviewText;

    private String funFact;

    private Long userId;
}
