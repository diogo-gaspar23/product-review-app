package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Review;
import com.isep.acme.repositories.ReviewRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Profile("neo4j")
public interface ReviewRepositoryNeo4j extends ReviewRepository, Neo4jRepository<Review, Long> {
}
