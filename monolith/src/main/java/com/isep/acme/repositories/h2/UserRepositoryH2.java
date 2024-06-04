package com.isep.acme.repositories.h2;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.User;
import com.isep.acme.repositories.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "users")
@Profile("h2")
public interface UserRepositoryH2 extends UserRepository, JpaRepository<User, Long> {

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#p0.userId", condition = "#p0.userId != null"),
            @CacheEvict(key = "#p0.username", condition = "#p0.username != null")})
    <S extends User> S save(S entity);

    @Override
    @Cacheable
    Optional<User> findById(Long userId);

    @Cacheable
    default User getById(final Long userId) {
        final Optional<User> optionalUser = findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        if (!optionalUser.get().isEnabled()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        return optionalUser.get();
    }

    @Cacheable
    Optional<User> findByUsername(String username);


}