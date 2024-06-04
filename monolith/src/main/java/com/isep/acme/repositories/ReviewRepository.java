package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository {

    Optional<List<Review>> findByProductId(Product product);

    Optional<List<Review>> findPendingReviews();

    Optional<List<Review>> findActiveReviews();

    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    Optional<List<Review>> findByUserId(User user);

    <S extends Review> S save(S entity);

    <S extends Review> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Review> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<Review> findAll();

    Iterable<Review> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(Review entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Review> entities);

    void deleteAll();
}
