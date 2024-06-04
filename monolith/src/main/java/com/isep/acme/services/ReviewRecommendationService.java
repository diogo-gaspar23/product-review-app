package com.isep.acme.services;

import com.isep.acme.model.Review;
import com.isep.acme.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRecommendationService {

    List<ReviewDTO> getRecommendedReviews(List<Review> reviews);
}
