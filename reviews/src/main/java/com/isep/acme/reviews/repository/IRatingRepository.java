package com.isep.acme.reviews.repository;

import com.isep.acme.reviews.model.Rating;

import java.util.Optional;

public interface IRatingRepository {
    Optional<Rating> findByRate(Double v);

    Rating save(Rating rating);
}
