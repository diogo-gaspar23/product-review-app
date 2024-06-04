package com.isep.acme.model.neo4j;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Node
@Profile("neo4j")
public class ReviewNeo4j {

    @Id @GeneratedValue
    private Long idReview;

    private long version;

    private String approvalStatus;

    private String reviewText;

    private List<VoteNeo4j> upVote;

    private List<VoteNeo4j> downVote;

    private String report;

    private LocalDate publishingDate;

    private String funFact;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private ProductNeo4j product;

    @Relationship(type = "MADE_BY", direction = Relationship.Direction.OUTGOING)
    private UserNeo4j user;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private RatingNeo4j rating;

    protected ReviewNeo4j() {
    }

    public ReviewNeo4j(final Long idReview, final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public ReviewNeo4j(final Long idReview, final long version, final String approvalStatus, final String reviewText, final List<VoteNeo4j> upVote, final List<VoteNeo4j> downVote, final String report, final LocalDate publishingDate, final String funFact, ProductNeo4j product, RatingNeo4j rating, UserNeo4j user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public ReviewNeo4j(final String reviewText, LocalDate publishingDate, ProductNeo4j product, String funFact, RatingNeo4j rating, UserNeo4j user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
        this.upVote = new ArrayList<>();
        this.downVote = new ArrayList<>();
    }

    public Long getIdReview() {
        return idReview;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public Boolean setApprovalStatus(String approvalStatus) {

        if (approvalStatus.equalsIgnoreCase("pending") ||
                approvalStatus.equalsIgnoreCase("approved") ||
                approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public String getReviewText() {
        return reviewText;
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

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public long getVersion() {
        return version;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public void setProduct(ProductNeo4j product) {
        this.product = product;
    }

    public ProductNeo4j getProduct() {
        return product;
    }

    public UserNeo4j getUser() {
        return user;
    }

    public void setUser(UserNeo4j user) {
        this.user = user;
    }

    public RatingNeo4j getRating() {
        if (rating == null) {
            return new RatingNeo4j(0.0);
        }
        return rating;
    }

    public void setRating(RatingNeo4j rating) {
        this.rating = rating;
    }

    public List<VoteNeo4j> getUpVote() {
        return upVote;
    }

    public void setUpVote(List<VoteNeo4j> upVote) {
        this.upVote = upVote;
    }

    public List<VoteNeo4j> getDownVote() {
        return downVote;
    }

    public void setDownVote(List<VoteNeo4j> downVote) {
        this.downVote = downVote;
    }

    public boolean addUpVote(VoteNeo4j upVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.upVote.contains(upVote)) {
            this.upVote.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(VoteNeo4j downVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.downVote.contains(downVote)) {
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }
}
