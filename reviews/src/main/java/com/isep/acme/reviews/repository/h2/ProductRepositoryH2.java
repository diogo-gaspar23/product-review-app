package com.isep.acme.reviews.repository.h2;

import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.repository.IProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("h2")
public interface ProductRepositoryH2 extends IProductRepository, JpaRepository<Product, Long> {

    @Override
    Optional<Product> findBySku(String sku);

    @Override
    @Transactional
    @Modifying
    Integer deleteBySku(String sku);
}
