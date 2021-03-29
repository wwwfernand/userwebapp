package com.local.userwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.local.userwebapp.error.UserAlreadyExistException;
import com.local.userwebapp.model.User;
import com.local.userwebapp.repository.UserRepository;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /** The b crypt password encoder. */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * ユーザー保存はServiceとして行う
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * ユーザー検索はServiceとして行う
     *
     * @param username the username
     * @return the user
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
