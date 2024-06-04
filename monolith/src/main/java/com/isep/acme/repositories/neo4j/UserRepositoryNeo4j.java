package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.User;
import com.isep.acme.repositories.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@CacheConfig(cacheNames = "users")
@Profile("neo4j")
public interface UserRepositoryNeo4j extends UserRepository, Neo4jRepository<User, Long> {
}