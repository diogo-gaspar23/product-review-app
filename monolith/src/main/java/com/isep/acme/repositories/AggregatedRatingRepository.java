package com.isep.acme.repositories;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AggregatedRatingRepository {

    Optional<AggregatedRating> findByProductId(Product product);

    public <S extends AggregatedRating> S save(S entity);

    public <S extends AggregatedRating> Iterable<S> saveAll(Iterable<S> entities);

    public Optional<AggregatedRating> findById(Long aLong);

    public boolean existsById(Long aLong);

    public Iterable<AggregatedRating> findAll();

    public Iterable<AggregatedRating> findAllById(Iterable<Long> longs);

    public long count();

    public void deleteById(Long aLong);

    public void delete(AggregatedRating entity);

    public void deleteAllById(Iterable<? extends Long> longs);

    public void deleteAll(Iterable<? extends AggregatedRating> entities);

    public void deleteAll();
}
