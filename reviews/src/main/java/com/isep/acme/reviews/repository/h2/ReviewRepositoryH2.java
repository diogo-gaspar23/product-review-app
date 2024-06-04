package com.isep.acme.reviews.repository.h2;

import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.model.Review;
import com.isep.acme.reviews.repository.IReviewRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("h2")
public interface ReviewRepositoryH2 extends IReviewRepository, JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product.productId=:productId ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByProductId(Long productId);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus IN ('NO_APPROVALS', 'MISSING_ONE_APPROVAL')")
    Optional<List<Review>> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='APPROVED'")
    Optional<List<Review>> findActiveReviews();

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    @Query("SELECT r FROM Review r WHERE r.user.userId=:userId ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByUserId(Long userId);

    Optional<Review> findByIdReview(Long reviewId);

    Boolean deleteByIdReview(Long reviewId);
}
