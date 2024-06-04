package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    Optional<Product> getCatalog();

    void deleteBySku(@Param("sku") String sku);

    Product updateBySku(@Param("sku") String sku);

    public <S extends Product> S save(S entity);

    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Product> findById(Long productID);

    public boolean existsById(Long aLong);

    public Iterable<Product> findAll();

    public Iterable<Product> findAllById(Iterable<Long> longs);

    public long count();

    public void deleteById(Long aLong);

    public void delete(Product entity);

    public void deleteAllById(Iterable<? extends Long> longs);

    public void deleteAll(Iterable<? extends Product> entities);

    public void deleteAll();

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}

