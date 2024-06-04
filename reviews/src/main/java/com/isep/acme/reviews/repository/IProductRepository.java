package com.isep.acme.reviews.repository;

import com.isep.acme.reviews.model.Product;

import java.util.Optional;

public interface IProductRepository {

    Optional<Product> findBySku(String sku);

    Product save(Product product);

    Optional<Product> findById(Long id);

    Integer deleteBySku(String sku);
}
