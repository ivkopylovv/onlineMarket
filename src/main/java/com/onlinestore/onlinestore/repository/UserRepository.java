package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByLogin(String login);
    Optional<User> findById(Long id);
    boolean existsById(Long id);
}
