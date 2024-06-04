package com.isep.acme.reviews.repository;

import com.isep.acme.reviews.model.AppUser;

import java.util.Optional;

public interface IUserRepository {

    AppUser save(AppUser user);

    void deleteByUserId(Long userId);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByUserId(Long userId);
}
