package com.isep.acme.reviews.service;

import com.isep.acme.reviews.dto.ProductDTO;

public interface IProductService {

    void save(ProductDTO productDTO);

    void delete(ProductDTO productDTO);
}
