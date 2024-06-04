package com.isep.acme.reviews.service.impl;

import com.isep.acme.reviews.dto.ProductDTO;
import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.repository.IProductRepository;
import com.isep.acme.reviews.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void save(ProductDTO productDTO) {
        Optional<Product> optional = this.productRepository.findBySku(productDTO.getSku());

        Product product;

        if (optional.isEmpty()) {
            product = new Product(
                    productDTO.getSku(),
                    productDTO.getDesignation(),
                    productDTO.getDescription());
        } else {
            product = optional.get();

            product.setSku(productDTO.getSku());
            product.setDesignation(productDTO.getDesignation());
            product.setDescription(product.getDescription());
        }

        this.productRepository
                .save(product);
    }

    @Override
    public void delete(ProductDTO productDTO) {
        this.productRepository.deleteBySku(productDTO.getSku());
    }
}
