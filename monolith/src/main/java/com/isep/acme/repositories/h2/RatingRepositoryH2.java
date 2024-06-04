package com.isep.acme.repositories.h2;

import com.isep.acme.model.Rating;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Profile("h2")
public interface RatingRepositoryH2 extends RatingRepository, JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.rate=:rate")
    Optional<Rating> findByRate(Double rate);

}
