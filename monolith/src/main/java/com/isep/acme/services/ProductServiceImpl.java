package com.isep.acme.services;

import com.isep.acme.model.Product;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;
import com.isep.acme.model.skugenerator.ISkuGenerator;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ISkuGenerator skuGenerator;

    @Override
    public Optional<Product> getProductBySku(final String sku) {

        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        if (product.isEmpty())
            return Optional.empty();
        else
            return Optional.of(product.get().toDto());
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @Override
    public ProductDTO create(final Product product) {
        if (product.getSku() != null) {
            return createWithSku(product);
        }

        return createWithGeneratedSku(product);
    }

    private ProductDTO createWithSku(final Product product) {
        final Product p = new Product(product.getSku(), product.getDesignation(), product.getDescription());

        return repository.save(p).toDto();
    }

    private ProductDTO createWithGeneratedSku(final Product product) {
        String sku = skuGenerator.generate(product.getDesignation());

        while (repository.findBySku(sku).isPresent()) {
            sku = skuGenerator.generate(product.getDesignation());
        }

        System.out.println(sku);

        final Product p = new Product(sku, product.getDesignation(), product.getDescription());

        return repository.save(p).toDto();
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {

        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if (productToUpdate.isEmpty()) return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = repository.save(productToUpdate.get());

        return productUpdated.toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    @Override
    public ProductDTO findById(Long id) {
        return null;
    }
}
