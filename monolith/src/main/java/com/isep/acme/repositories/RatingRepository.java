package com.isep.acme.repositories;

import com.isep.acme.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository {

    Optional<Rating> findByRate(Double rate);

    <S extends Rating> S save(S entity);

    <S extends Rating> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Rating> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Rating> findAll();

    Iterable<Rating> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Rating entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Rating> entities);

    void deleteAll();
}
