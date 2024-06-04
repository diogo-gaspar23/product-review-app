package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Rating;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Profile("neo4j")
public interface RatingRepositoryNeo4j extends RatingRepository, Neo4jRepository<Rating, Long> {
}
