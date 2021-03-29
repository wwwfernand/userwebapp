package com.local.userwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.local.userwebapp.model.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}