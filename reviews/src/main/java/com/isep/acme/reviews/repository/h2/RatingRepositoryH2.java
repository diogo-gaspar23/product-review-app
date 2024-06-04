package com.isep.acme.reviews.repository.h2;

import com.isep.acme.reviews.model.Rating;
import com.isep.acme.reviews.repository.IRatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("h2")
public interface RatingRepositoryH2 extends IRatingRepository, JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.rate=:rate")
    Optional<Rating> findByRate(Double rate);

}
