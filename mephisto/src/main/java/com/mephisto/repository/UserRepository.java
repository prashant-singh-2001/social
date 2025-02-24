package com.mephisto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephisto.model.User;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and custom query methods.
 * 
 * @author
 *         on February 24, 2025.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username.
     * 
     * @param username the username of the user to find
     * @return the user with the specified username
     */
    User findByUsername(String username);
}