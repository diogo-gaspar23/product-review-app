package com.isep.acme.products.service;

import com.isep.acme.products.dto.CreateProductDTO;
import com.isep.acme.products.dto.ProductDTO;

public interface IProductService {

    ProductDTO getProductBySku(String sku);

    ProductDTO findBySku(String sku);

    Iterable<ProductDTO> findByDesignation(String designation);

    Iterable<ProductDTO> getCatalog();

    ProductDTO getDetails(String sku);

    ProductDTO create(CreateProductDTO product);

    ProductDTO updateBySku(String sku, CreateProductDTO product);

    void deleteBySku(String sku);

    ProductDTO findById(Long id);

    ProductDTO updatePublicationPermission(String sku,String user);
}
