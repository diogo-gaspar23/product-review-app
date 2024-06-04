package com.isep.acme.reviews.service.impl;

import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.dto.ReviewMapper;
import com.isep.acme.reviews.model.AppUser;
import com.isep.acme.reviews.model.Review;
import com.isep.acme.reviews.model.Vote;
import com.isep.acme.reviews.repository.IUserRepository;
import com.isep.acme.reviews.service.ReviewRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("recommended2")
@Service
public class ReviewRecommendationServiceImplAlg2 implements ReviewRecommendationService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<ReviewDTO> getRecommendedReviews(List<Review> reviews, String ...args) {

        if (reviews.isEmpty()) {
            return Collections.emptyList();
        }

        String username = args[0];

        Optional<AppUser> optional = userRepository.findByUsername(username);

        if (optional.isEmpty()) return new ArrayList<>();

        Long userId = optional.get().getUserId();

        List<Review> recommendedReviews = new ArrayList<>();

        Map<Long, String> requestUserVotedReviews = getVotedReviews(reviews, userId);

        for (Review review : reviews) {
            if (requestUserVotedReviews.get(review.getIdReview()) == null || review.getUser().getUserId() == userId) {
                continue;
            }
            Map<Long, String> currUserVotedReviews = getVotedReviews(reviews, review.getUser().getUserId(), requestUserVotedReviews);

            int percentage = calculateEqualVotePercentage(requestUserVotedReviews, currUserVotedReviews);

            if (percentage > 50) {
                recommendedReviews.add(review);
            }
        }

        return ReviewMapper.toDtoList(recommendedReviews);
    }

    private int calculateEqualVotePercentage(Map<Long, String> requestUserVotedReviews, Map<Long, String> currUserVotedReviews) {
        final int totalReviews = currUserVotedReviews.size();
        int sameVoteReviews = 0;

        for (Long reviewId : currUserVotedReviews.keySet()) {
            if (Objects.equals(requestUserVotedReviews.get(reviewId), currUserVotedReviews.get(reviewId))) {
                sameVoteReviews++;
            }
        }

        float ratio = (float) sameVoteReviews / (float) totalReviews;

        return (int) (ratio * 100);
    }

    private Map<Long, String> getVotedReviews(List<Review> reviews, long userId) {
        Map<Long, String> votedReviews = new HashMap<>();
        for (Review review : reviews) {
            checkVotes(userId, votedReviews, review);
        }

        return votedReviews;
    }

    private Map<Long, String> getVotedReviews(List<Review> reviews, long userId, Map<Long, String> requestUserVotedReviews) {
        Map<Long, String> votedReviews = new HashMap<>();
        for (Review review : reviews) {
            if (requestUserVotedReviews.get(review.getIdReview()) == null) {
                continue;
            }

            checkVotes(userId, votedReviews, review);
        }

        return votedReviews;
    }

    private void checkVotes(long userId, Map<Long, String> votedReviews, Review review) {
        List<Vote> upVotes = review.getUpVote();
        List<Vote> downVotes = review.getDownVote();
        for (Vote upVote : upVotes) {
            if (userId == upVote.getUserId()) {
                votedReviews.put(review.getIdReview(), "upvote");
                return;
            }
        }

        for (Vote downVote : downVotes) {
            if (userId == downVote.getUserId()) {
                votedReviews.put(review.getIdReview(), "downvote");
                return;
            }
        }
    }
}
