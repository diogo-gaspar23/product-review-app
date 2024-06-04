package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Profile("neo4j")
public interface ProductRepositoryNeo4j extends ProductRepository, Neo4jRepository<Product, Long> {
}

