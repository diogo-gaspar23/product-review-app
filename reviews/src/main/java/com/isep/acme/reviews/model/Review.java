package com.isep.acme.reviews.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Profile("h2")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long idReview;

    @Version
    private long version;

    @Column(name = "approval_status", nullable = false)
    private String approvalStatus;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(nullable = true)
    private String report;

    @Column(name = "publishing_date", nullable = false)
    private LocalDate publishingDate;

    @Column(name = "fun_fact",nullable = false)
    private String funFact;

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> upVote;

    @ElementCollection
    @Column(nullable = true)
    private List<Vote> downVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText, final String report, final LocalDate publishingDate, final String funFact, Product product, Rating rating, AppUser user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public Review(final String reviewText, LocalDate publishingDate, Product product, String funFact, AppUser user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("NO_APPROVALS");
        setFunFact(funFact);
        setUser(user);
    }

    public Review(final String reviewText, LocalDate publishingDate, Product product, String funFact, Rating rating, AppUser user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("NO_APPROVALS");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
    }

    public Boolean setApprovalStatus(String approvalStatus) {

        if (approvalStatus.equalsIgnoreCase("NO_APPROVALS") ||
                approvalStatus.equalsIgnoreCase("MISSING_ONE_APPROVAL") ||
                approvalStatus.equalsIgnoreCase("APPROVED")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public void setReviewText(String reviewText) {
        if (reviewText == null || reviewText.isBlank()) {
            throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
        }
        if (reviewText.length() > 2048) {
            throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
        }

        this.reviewText = reviewText;
    }

    public void setReport(String report) {
        if (report.length() > 2048) {
            throw new IllegalArgumentException("Report must not be greater than 2048 characters.");
        }
        this.report = report;
    }

    public Rating getRating() {
        if (rating == null) {
            return new Rating(0.0);
        }
        return rating;
    }

}
