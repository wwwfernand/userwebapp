package com.local.userwebapp.service;

import org.springframework.security.core.Authentication;

import com.local.userwebapp.error.UserNotFoundException;

/**
 * The Interface LoginService.
 */
public interface LoginService {
    
    /**
     * ログイン認証はServiceとして行う
     *
     * @param username the username
     * @param password the password
     * @return the authentication
     * @throws UserNotFoundException the user not found exception
     */
    Authentication authenticate(String username, String password) throws UserNotFoundException;
}
