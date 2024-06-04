package com.isep.acme.reviews.repository.h2;

import com.isep.acme.reviews.model.AppUser;
import com.isep.acme.reviews.repository.IUserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("h2")
public interface UserRepositoryH2 extends IUserRepository, JpaRepository<AppUser, Long> {

    @Override
    void deleteByUserId(Long userId);

    @Override
    Optional<AppUser> findByUsername(String username);
}