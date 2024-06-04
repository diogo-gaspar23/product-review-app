package com.isep.acme.services;

import com.isep.acme.model.Review;

import com.isep.acme.model.Vote;
import com.isep.acme.model.dto.ReviewDTO;
import com.isep.acme.model.dto.ReviewMapper;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.ReviewRecommendationService;
import com.isep.acme.services.ReviewRecommendationServiceImplAlg1;
import com.isep.acme.services.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("recommended1")
public class ReviewServiceImplIntegrationTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewRecommendationService recommendationAlgorithm;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private List<Review> mockedList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        // Reviews mockadas
        Review review1 = Mockito.mock(Review.class);
        Review review2 = Mockito.mock(Review.class);
        Review review3 = Mockito.mock(Review.class);
        Review review4 = Mockito.mock(Review.class);
        Review review5 = Mockito.mock(Review.class);

        /*
        // Review que deve ser escolhida a posta em 1o da lista:
        Mockito.when(review1.getUpVote()).thenReturn(Arrays.asList(
                new Vote("up", 1L),
                new Vote("up", 2L),
                new Vote("up", 3L),
                new Vote("up", 4L),
                new Vote("up", 5L),
                new Vote("up", 6L)
        ));
        Mockito.when(review1.getDownVote()).thenReturn(List.of());

        // Review que deve ser escolhida a posta em 2o da lista:
        Mockito.when(review2.getUpVote()).thenReturn(Arrays.asList(
                new Vote("up", 1L),
                new Vote("up", 2L),
                new Vote("up", 3L),
                new Vote("up", 4L),
                new Vote("up", 5L)
        ));
        Mockito.when(review2.getDownVote()).thenReturn(List.of());

        // Review que deve ser escolhida a posta em 3o da lista:
        Mockito.when(review3.getUpVote()).thenReturn(Arrays.asList(
                new Vote("up", 1L),
                new Vote("up", 2L),
                new Vote("up", 3L),
                new Vote("up", 4L)
        ));
        Mockito.when(review3.getDownVote()).thenReturn(List.of());

        // Review que nao deve ser escolhida pois tem menos de 4 votos
        Mockito.when(review4.getUpVote()).thenReturn(Arrays.asList(
                new Vote("up", 1L),
                new Vote("up", 3L),
                new Vote("up", 4L)
        ));
        Mockito.when(review4.getDownVote()).thenReturn(List.of());

        // Review que nao deve nao ser escolhida pois tem menos de 65% de upvotes
        Mockito.when(review5.getUpVote()).thenReturn(Arrays.asList(
                new Vote("up", 1L),
                new Vote("up", 2L),
                new Vote("up", 3L)
        ));

        Mockito.when(review5.getDownVote()).thenReturn(Arrays.asList(
                new Vote("down", 4L),
                new Vote("down", 5L),
                new Vote("down", 6L)
        ));


         */
        mockedList = Arrays.asList(review1, review2, review3, review4, review5);
    }

    @Test
    public void testFindRecommendedReviews() {
        MockitoAnnotations.initMocks(this);
        when(reviewRepository.findActiveReviews()).thenReturn(Optional.of(mockedList));
        // Método a testar
        List<ReviewDTO> recommendedReviews = reviewService.findRecommendedReviews();
        assertEquals(0, recommendedReviews.size());

    }
}
