package com.isep.acme.reviews.service.impl;

import com.isep.acme.reviews.dto.CreateReviewDTO;
import com.isep.acme.reviews.dto.ReviewDTO;
import com.isep.acme.reviews.dto.ReviewMapper;
import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.model.Rating;
import com.isep.acme.reviews.model.Review;
import com.isep.acme.reviews.model.AppUser;
import com.isep.acme.reviews.model.domainEvents.ReviewCreatedOrUpdatedEvent;
import com.isep.acme.reviews.model.domainEvents.ReviewDeletedEvent;
import com.isep.acme.reviews.model.domainEvents.ReviewPublishedEvent;
import com.isep.acme.reviews.publisher.RabbitMQProducer;
import com.isep.acme.reviews.repository.IProductRepository;
import com.isep.acme.reviews.repository.IReviewRepository;
import com.isep.acme.reviews.repository.IUserRepository;
import com.isep.acme.reviews.service.IReviewService;
import com.isep.acme.reviews.service.ReviewRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private RabbitMQProducer producer;

    @Autowired
    private IReviewRepository repository;

    @Autowired
    private IProductRepository pRepository;

    @Autowired
    private IUserRepository uRepository;

    @Autowired
    private ReviewRecommendationService recommendationAlgorithm;

    @Override
    public Iterable<ReviewDTO> getAll() {
        return ReviewMapper.toDtoList(repository.findAll());
    }

    @Override
    public ReviewDTO create(final CreateReviewDTO createReviewDTO, String sku) {

        final Product product = pRepository
                .findBySku(sku)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        final AppUser user = uRepository
                .findByUserId(createReviewDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        LocalDate date = LocalDate.now();

        Review review = new Review(createReviewDTO.getReviewText(), date, product, createReviewDTO.getFunFact(), user);

        review = repository.save(review);

        ReviewDTO reviewDTO = ReviewMapper.toDTO(review);

        producer.sendReviewCreatedOrUpdatedMessage(new ReviewCreatedOrUpdatedEvent(reviewDTO));

        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getReviewsOfProduct(String sku, String status) {

        Optional<Product> product = pRepository.findBySku(sku);
        if (product.isEmpty()) return null;

        Optional<List<Review>> r = repository.findByProductIdStatus(product.get(), status);

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r.get());
    }

    @Override
    public Double getWeightedAverage(Long productId) {

        Optional<List<Review>> r = repository.findByProductId(productId);

        if (r.isEmpty()) return 0.0;

        double sum = 0;

        for (Review rev : r.get()) {
            Rating rate = rev.getRating();

            if (rate != null) {
                sum += rate.getRate();
            }
        }

        return sum / r.get().size();
    }

    @Override
    public Boolean deleteReview(Long reviewId) {

        Optional<Review> rev = repository.findByIdReview(reviewId);

        if (rev.isEmpty()) {
            return null;
        }
        Review r = rev.get();

        producer.sendReviewDeletedMessage(new ReviewDeletedEvent(ReviewMapper.toDTO(r)));

        return repository.deleteByIdReview(reviewId);
    }

    @Override
    public List<ReviewDTO> findPendingReview() {

        Optional<List<Review>> r = repository.findPendingReviews();

        if (r.isEmpty()) {
            return null;
        }

        return ReviewMapper.toDtoList(r.get());
    }

    @Override
    public List<ReviewDTO> findReviewsByUser(Long userId) {
        uRepository
            .findByUserId(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Review> r = repository
                .findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reviews not found"));

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public List<ReviewDTO> findRecommendedActiveReviews(String username) {
        List<Review> reviews = repository
                .findActiveReviews()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reviews not found"));

        return recommendationAlgorithm.getRecommendedReviews(reviews, username);
    }

    @Override
    public List<ReviewDTO> findRecommendedPendingReviews(String username) {
        List<Review> reviews = repository
                .findPendingReviews()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reviews not found"));

        return recommendationAlgorithm.getRecommendedReviews(reviews, username);
    }

    @Override
    public ReviewDTO acceptReview(Long reviewId, String username) {
        Review review = repository
                .findByIdReview(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found."));

        final List<Review> reviews = repository
                .findPendingReviews()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        final List<ReviewDTO> dtoList = recommendationAlgorithm.getRecommendedReviews(reviews, username);

        boolean flag = false;

        for (ReviewDTO reviewDTO: dtoList) {
            if (Objects.equals(reviewDTO.getIdReview(), review.getIdReview())) {
                flag = true;
                break;
            }
        }

        if (!flag) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review was not recommended");

        switch (review.getApprovalStatus()) {
            case "MISSING_ONE_APPROVAL" -> {
                review.setApprovalStatus("APPROVED");
                ReviewDTO reviewDTO = saveReview(review);
                producer.sendReviewPublishedMessage(new ReviewPublishedEvent(reviewDTO));
                return reviewDTO;
            }
            case "NO_APPROVALS" -> {
                review.setApprovalStatus("MISSING_ONE_APPROVAL");
                return saveReview(review);
            }
            default -> throw new ResponseStatusException(HttpStatus.CONFLICT, "The chosen review does not have a valid status.");
        }
    }

    private ReviewDTO saveReview(Review review) {
        Review savedReview = this.repository.save(review);

        return ReviewMapper.toDTO(savedReview);
    }
}