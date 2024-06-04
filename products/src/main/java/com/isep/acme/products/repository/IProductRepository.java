package com.isep.acme.products.repository;

import com.isep.acme.products.model.Product;

import java.util.Optional;

public interface IProductRepository {

    Optional<Product> findBySku(String sku);

    Iterable<Product> findByDesignation(String designation);

    Iterable<Product> findAll();

    Product save(Product product);

    void deleteBySku(String sku);

    Optional<Product> findById(Long id);
}
