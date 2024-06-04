package com.isep.acme.repositories;

import com.isep.acme.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    <S extends User> S save(S entity);

    <S extends User> Iterable<S> saveAll(Iterable<S> entities);

    Optional<User> findById(Long userId);

    boolean existsById(Long aLong);

    Iterable<User> findAll();

    Iterable<User> findAllById(Iterable<Long> longs);

    long count();

    void deleteById(Long aLong);

    void delete(User entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends User> entities);

    void deleteAll();

    User getById(final Long userId);

    Optional<User> findByUsername(String username);


}