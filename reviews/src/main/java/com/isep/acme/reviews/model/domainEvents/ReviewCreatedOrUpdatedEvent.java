package com.isep.acme.reviews.model.domainEvents;

import com.isep.acme.reviews.dto.ProductDTO;
import com.isep.acme.reviews.dto.RatingDTO;
import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.dto.UserDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewCreatedOrUpdatedEvent {

    private final ReviewDTO reviewDTO;

    public ReviewCreatedOrUpdatedEvent(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public ReviewCreatedOrUpdatedEvent(
        Long idReview,
        String approvalStatus,
        String reviewText,
        String report,
        LocalDate publishingDate,
        String funFact,
        RatingDTO rating,
        ProductDTO product,
        UserDTO user
    ) {
        this.reviewDTO = new ReviewDTO(
            idReview,
            approvalStatus,
            reviewText,
            report,
            publishingDate,
            funFact,
            rating,
            product,
            user
        );
    }

    @Override
    public String toString() {
        return "ReviewCreatedOrUpdatedEvent{" +
                "reviewDTO=" + reviewDTO +
                '}';
    }
}
