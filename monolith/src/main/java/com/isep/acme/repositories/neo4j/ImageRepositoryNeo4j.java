package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.ProdImage;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Profile("neo4j")
public interface ImageRepositoryNeo4j extends ImageRepository, Neo4jRepository<ProdImage, Long> {
}
