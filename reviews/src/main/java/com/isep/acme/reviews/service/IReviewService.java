package com.isep.acme.reviews.service;

import com.isep.acme.reviews.dto.CreateReviewDTO;
import com.isep.acme.reviews.dto.ReviewDTO;

import java.util.List;

public interface IReviewService {

    Iterable<ReviewDTO> getAll();

    List<ReviewDTO> getReviewsOfProduct(String sku, String status);

    ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

    Double getWeightedAverage(Long productId);

    Boolean deleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    List<ReviewDTO> findReviewsByUser(Long userId);

    List<ReviewDTO> findRecommendedActiveReviews(String username);

    List<ReviewDTO> findRecommendedPendingReviews(String username);

    ReviewDTO acceptReview(Long reviewId, String username);
}
