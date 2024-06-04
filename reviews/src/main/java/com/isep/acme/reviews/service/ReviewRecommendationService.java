package com.isep.acme.reviews.service;

import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRecommendationService {

    List<ReviewDTO> getRecommendedReviews(List<Review> reviews, String ...args);
}
