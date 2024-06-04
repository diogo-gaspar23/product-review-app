package com.isep.acme.model.skugenerator;

import org.springframework.stereotype.Service;

@Service
public interface ISkuGenerator {

    String generate(String designation);
}