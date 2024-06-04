package com.isep.acme.reviews.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReviewDTO {

    private Long idReview;

    private String approvalStatus;

    private String reviewText;

    private String report;

    private LocalDate publishingDate;

    private String funFact;

    private RatingDTO rating;

    private ProductDTO product;

    private UserDTO user;

    public ReviewDTO(Long idReview, String approvalStatus, String reviewText, String report, LocalDate publishingDate, String funFact, RatingDTO rating, ProductDTO product, UserDTO user) {
        this.idReview = idReview;
        this.approvalStatus = approvalStatus;
        this.reviewText = reviewText;
        this.report = report;
        this.publishingDate = publishingDate;
        this.funFact = funFact;
        this.rating = rating;
        this.product = product;
        this.user = user;
    }
}
