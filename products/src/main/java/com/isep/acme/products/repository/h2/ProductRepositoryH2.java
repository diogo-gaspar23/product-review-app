package com.isep.acme.products.repository.h2;

import com.isep.acme.products.model.Product;
import com.isep.acme.products.repository.IProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("h2")
public interface ProductRepositoryH2 extends IProductRepository, JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    Iterable<Product> findByDesignation(String designation);

    @Transactional
    @Modifying
    void deleteBySku(String sku);
}
