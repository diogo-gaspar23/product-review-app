package com.isep.acme.reviews.service.impl;

import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.dto.ReviewMapper;
import com.isep.acme.reviews.model.Review;
import com.isep.acme.reviews.model.Vote;
import com.isep.acme.reviews.service.ReviewRecommendationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Profile("recommended1")
@Service
public class ReviewRecommendationServiceImplAlg1 implements ReviewRecommendationService {
    @Override
    public List<ReviewDTO> getRecommendedReviews(List<Review> reviews, String ...args) {


        if (reviews.isEmpty()) {
            return Collections.emptyList();
        }

        System.out.println("Lista Inicial:" + reviews);

        // Filtro 1: Mínimo de 4 upvotes
        List<Review> filteredReviews = reviews.stream()
                .filter(review -> review.getUpVote().size() >= 4)
                .collect(Collectors.toList());

        // Filtro 2: Pelo menos 65% dos votos são upvotes
        filteredReviews = filteredReviews.stream()
                .filter(review -> {
                    List<Vote> upVotes = review.getUpVote();
                    List<Vote> downVotes = review.getDownVote();
                    double totalVotes = upVotes.size() + downVotes.size();
                    double upVotePercentage = (upVotes.size() / totalVotes) * 100;
                    return upVotePercentage >= 65;
                })
                .collect(Collectors.toList());

        // Ordenar as avaliações pelo número de upvotes
        filteredReviews.sort(Comparator.comparingInt(review -> review.getUpVote().size()));

        // Reverter a lista para obter a classificação decrescente
        Collections.reverse(filteredReviews);

        // Converter as Review para ReviewDTO
        List<ReviewDTO> recommendedReviews = ReviewMapper.toDtoList(filteredReviews);
        System.out.println(recommendedReviews);

        return recommendedReviews;

    }
}
