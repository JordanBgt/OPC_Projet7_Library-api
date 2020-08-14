package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to handle User DAO operations
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
