package com.isep.acme.products.controller;

import com.isep.acme.products.dto.CreateProductDTO;
import com.isep.acme.products.dto.ProductDTO;
import com.isep.acme.products.publisher.RabbitMQProducer;
import com.isep.acme.products.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService service;

    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
        final var products = service.getCatalog();

        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {

        final ProductDTO product = service.findBySku(sku);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation) {

        final Iterable<ProductDTO> products = service.findByDesignation(designation);

        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody CreateProductDTO productDTO) {
        final ProductDTO product = service.create(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> Update(@PathVariable("sku") final String sku, @RequestBody final CreateProductDTO product) {

        final ProductDTO productDTO = service.updateBySku(sku, product);

        if (productDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else {
            return ResponseEntity.ok().body(productDTO);
        }
    }

    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> delete(@PathVariable("sku") final String sku) {
        service.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/allowPublication/{sku}")
    public ResponseEntity<ProductDTO> Update(@PathVariable("sku") final String sku, @RequestHeader("User") String user) {

        final ProductDTO productDTO = service.updatePublicationPermission(sku,user);

        if (productDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else {
            return ResponseEntity.ok().body(productDTO);
        }
    }

}
