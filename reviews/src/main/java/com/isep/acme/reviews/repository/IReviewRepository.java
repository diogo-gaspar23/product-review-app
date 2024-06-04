package com.isep.acme.reviews.repository;

import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository {
    Review save(Review review);

    Optional<Review> findByIdReview(Long reviewId);

    Optional<List<Review>> findActiveReviews();

    Boolean deleteByIdReview(Long reviewId);

    Optional<List<Review>> findPendingReviews();

    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    Optional<List<Review>> findByUserId(Long userId);

    Optional<List<Review>> findByProductId(Long productId);

    List<Review> findAll();
}
