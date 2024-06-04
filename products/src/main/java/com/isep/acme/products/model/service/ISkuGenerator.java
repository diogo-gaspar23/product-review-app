package com.isep.acme.products.model.service;

import org.springframework.stereotype.Service;

@Service
public interface ISkuGenerator {

    String generate(String designation);
}