package com.isep.acme.reviews.controller;

import com.isep.acme.reviews.dto.CreateReviewDTO;
import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.dto.UsernameDTO;
import com.isep.acme.reviews.service.IReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ReviewController {

    @Autowired
    private IReviewService rService;

    @GetMapping("/products/{sku}/reviews/{status}")
    public ResponseEntity<List<ReviewDTO>> findById(@PathVariable(value = "sku") final String sku, @PathVariable(value = "status") final String status) {

        final List<ReviewDTO> review = rService.getReviewsOfProduct(sku, status);

        return ResponseEntity.ok().body(review);
    }

    @GetMapping("/reviews/{userID}")
    public ResponseEntity<List<ReviewDTO>> findReviewByUser(@PathVariable(value = "userID") final Long userID) {

        final List<ReviewDTO> review = rService.findReviewsByUser(userID);

        return ResponseEntity.ok().body(review);
    }

    @PostMapping("/products/{sku}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "sku") final String sku, @RequestBody CreateReviewDTO createReviewDTO) {

        final var review = rService.create(createReviewDTO, sku);

        if (review == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) {
        Boolean rev = rService.deleteReview(reviewID);

        if (rev == null) return ResponseEntity.notFound().build();

        if (!rev) return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok().body(rev);
    }

    @GetMapping("/reviews/pending")
    public ResponseEntity<List<ReviewDTO>> getPendingReview() {
        List<ReviewDTO> r = rService.findPendingReview();

        return ResponseEntity.ok().body(r);
    }

    @PostMapping("/reviews/accept/{reviewId}")
    public ResponseEntity<ReviewDTO> postAcceptReview(@PathVariable(value = "reviewId") final Long reviewId, @RequestBody UsernameDTO username) {
        try {
            ReviewDTO rev = rService.acceptReview(reviewId, username.getUsername());

            return ResponseEntity.ok().body(rev);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reviews/recommended/active/{username}")
    public ResponseEntity<List<ReviewDTO>> getRecommendedActiveReviews(@PathVariable(value = "username") final String username) {
        List<ReviewDTO> r = rService.findRecommendedActiveReviews(username);

        return ResponseEntity.ok().body(r);
    }

    @GetMapping("/reviews/recommended/pending/{username}")
    public ResponseEntity<List<ReviewDTO>> getRecommendedPendingReviews(@PathVariable(value = "username") final String username) {
        List<ReviewDTO> r = rService.findRecommendedPendingReviews(username);

        return ResponseEntity.ok().body(r);
    }
}
