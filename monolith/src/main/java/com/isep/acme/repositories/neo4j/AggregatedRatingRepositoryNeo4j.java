package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.repositories.AggregatedRatingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@Profile("neo4j")
public interface AggregatedRatingRepositoryNeo4j extends AggregatedRatingRepository, Neo4jRepository<AggregatedRating, Long> {
}
