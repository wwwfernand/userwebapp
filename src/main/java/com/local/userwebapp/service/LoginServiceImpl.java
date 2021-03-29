package com.local.userwebapp.service;

import com.local.userwebapp.model.User;
import com.local.userwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.local.userwebapp.error.UserNotFoundException;


/**
 * The Class LoginServiceImpl.
 */
@Service
public class LoginServiceImpl implements LoginService {
    
    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * ログイン認証はServiceとして行う
     *
     * @param userName the user name
     * @param password the password
     * @return the authentication
     */
    @Override
    public Authentication authenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            if (auth == null)
            {
                throw new UserNotFoundException();
            }
            return auth;
        } catch (BadCredentialsException | LockedException | DisabledException ex) {
            throw new UserNotFoundException(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UserNotFoundException(ex);
        }
    }
}
