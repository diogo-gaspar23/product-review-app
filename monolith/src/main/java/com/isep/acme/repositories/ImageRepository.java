package com.isep.acme.repositories;

import com.isep.acme.model.ProdImage;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository {

    <S extends ProdImage> S save(S entity);

    <S extends ProdImage> Iterable<S> saveAll(Iterable<S> entities);

    Optional<ProdImage> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<ProdImage> findAll();

    Iterable<ProdImage> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(ProdImage entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends ProdImage> entities);

    void deleteAll();
}
