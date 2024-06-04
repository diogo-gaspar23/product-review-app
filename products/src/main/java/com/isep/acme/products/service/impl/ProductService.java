package com.isep.acme.products.service.impl;

import com.isep.acme.products.dto.CreateProductDTO;
import com.isep.acme.products.dto.ProductDTO;
import com.isep.acme.products.model.Product;
import com.isep.acme.products.model.domainEvents.ProductCreatedOrUpdatedEvent;
import com.isep.acme.products.model.domainEvents.ProductDeletedEvent;
import com.isep.acme.products.model.domainEvents.ProductPublishedEvent;
import com.isep.acme.products.model.service.ISkuGenerator;
import com.isep.acme.products.publisher.RabbitMQProducer;
import com.isep.acme.products.repository.IProductRepository;
import com.isep.acme.products.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ISkuGenerator skuGenerator;

    @Autowired
    private RabbitMQProducer producer;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDTO getProductBySku(String sku) {
        final Product product = repository
                .findBySku(sku)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO findBySku(String sku) {
        final Product product = repository
                .findBySku(sku)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");

        return mapper.map(product, ProductDTO.class);
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(mapper.map(pd, ProductDTO.class));
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(mapper.map(pd, ProductDTO.class));
        }

        return pDto;
    }

    public ProductDTO getDetails(String sku) {
        Product p = repository.findBySku(sku).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return mapper.map(p, ProductDTO.class);
    }


    @Override
    public ProductDTO create(final CreateProductDTO product) {
        if (product.sku != null) {
            ProductDTO productDTO = createWithSku(product);
            ProductCreatedOrUpdatedEvent event = new ProductCreatedOrUpdatedEvent(productDTO);
            producer.sendCreatedOrUpdatedProductMessage(event);
            return createWithSku(product);
        }
        ProductDTO productDTO = createWithGeneratedSku(product);
        ProductCreatedOrUpdatedEvent event = new ProductCreatedOrUpdatedEvent(productDTO);
        producer.sendCreatedOrUpdatedProductMessage(event);
        return productDTO;
    }

    private ProductDTO createWithSku(CreateProductDTO product) {
        final Product p = new Product(product.sku, product.designation, product.description);

        Product savedProduct = repository.save(p);

        return mapper.map(savedProduct, ProductDTO.class);
    }

    private ProductDTO createWithGeneratedSku(CreateProductDTO product) {
        String sku = skuGenerator.generate(product.designation);
        int count = 0;

        while (repository.findBySku(sku).isPresent()) {
            sku = skuGenerator.generate(product.designation);
            if (count++ > 10) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Product could not be created.");
            }
        }

        final Product p = new Product(sku, product.designation, product.description);

        Product savedProduct = repository.save(p);

        return mapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateBySku(String sku, CreateProductDTO product) {
        Product productToUpdate = repository
                .findBySku(sku)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        productToUpdate.updateProduct(product);

        Product updatedProduct = repository.save(productToUpdate);

        ProductCreatedOrUpdatedEvent event = new ProductCreatedOrUpdatedEvent(mapper.map(updatedProduct, ProductDTO.class));
        producer.sendCreatedOrUpdatedProductMessage(event);

        return mapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);
        ProductDeletedEvent event = new ProductDeletedEvent(productDTO);
        producer.sendDeletedProductMessage(event);
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = this.repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updatePublicationPermission(String sku,String user) {
        Product productToUpdatePublishPermitions = repository
                .findBySku(sku)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        productToUpdatePublishPermitions.setUsersThatAllowedPublication(user);
        if (productToUpdatePublishPermitions.getUsersThatAllowedPublication().size()>1){
            productToUpdatePublishPermitions.setIsPublished(true);
            ProductPublishedEvent event = new ProductPublishedEvent(mapper.map(productToUpdatePublishPermitions, ProductDTO.class));
           producer.sendPublishedProductMessage(event);
        }

        Product updatedProduct = repository.save(productToUpdatePublishPermitions);
        return mapper.map(updatedProduct, ProductDTO.class);
    }
}
