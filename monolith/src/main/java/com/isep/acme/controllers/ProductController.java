package com.isep.acme.controllers;

import com.isep.acme.model.Product;
import com.isep.acme.model.dto.ProductDTO;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final String productsServiceUrl;

    private final RestTemplate restTemplate;

    public ProductController(RestTemplateBuilder restTemplateBuilder, @Value("${products.service.url}") String productsServiceBaseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.productsServiceUrl = String.format("%s/products", productsServiceBaseUrl);
    }

    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
        ResponseEntity<ProductDTO[]> response = this.restTemplate.getForEntity(productsServiceUrl, ProductDTO[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            List<ProductDTO> productDTOList = Arrays.asList(Objects.requireNonNull(response.getBody()));
            return ResponseEntity.ok().body(productDTOList);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {
        ResponseEntity<ProductDTO> response = this.restTemplate.getForEntity(String.format("%s/%s", productsServiceUrl, sku), ProductDTO.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(response.getStatusCode(), "Product not found.");
        } else {
            return ResponseEntity.ok().body(response.getBody());
        }
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation) {
        ResponseEntity<ProductDTO[]> response = this.restTemplate.getForEntity(String.format("%s/%s", productsServiceUrl, designation), ProductDTO[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            List<ProductDTO> productDTOList = Arrays.asList(Objects.requireNonNull(response.getBody()));
            return ResponseEntity.ok().body(productDTOList);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody Product manager) {
        ResponseEntity<ProductDTO> response = this.restTemplate.postForEntity(productsServiceUrl, manager, ProductDTO.class);

        if(response.getStatusCode() == HttpStatus.CREATED) {
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity Update(@PathVariable("sku") final String sku, @RequestBody final Product product) {
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> entity = new HttpEntity<>(Json.pretty(product), headers);
        ResponseEntity<ProductDTO> response = restTemplate.exchange(String.format("%s/%s", productsServiceUrl, sku), HttpMethod.PUT, entity, ProductDTO.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity delete(@PathVariable("sku") final String sku) {
        this.restTemplate.delete(String.format("%s/%s", productsServiceUrl, sku));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}