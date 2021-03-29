package com.local.userwebapp.service;

import com.local.userwebapp.error.UserAlreadyExistException;
import com.local.userwebapp.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {
    
    /**
     * ユーザー保存はServiceとして行う
     *
     * @param user the user
     * @return the user
     * @throws UserAlreadyExistException the user already exist exception
     */
    User save(User user) throws UserAlreadyExistException;

    /**
     * ユーザー検索はServiceとして行う
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}
