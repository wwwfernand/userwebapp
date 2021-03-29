package com.local.userwebapp.test;

import com.local.userwebapp.config.TestDbConfig;
import com.local.userwebapp.config.TestIntegrationConfig;
import com.local.userwebapp.error.UserAlreadyExistException;
import com.local.userwebapp.error.UserNotFoundException;
import com.local.userwebapp.model.User;
import com.local.userwebapp.repository.UserRepository;
import com.local.userwebapp.service.LoginService;
import com.local.userwebapp.service.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDbConfig.class, TestIntegrationConfig.class })
public class LoginServiceIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @Before
    public void setup() throws UserAlreadyExistException {
        user = new User();
        user.setUsername("validUser");
        user.setPassword(bCryptPasswordEncoder.encode("validPassword"));
        user=userRepository.save(user);
    }


    @Test
    public void authenticateValidPasswordSuccess(){
        /* Todo: 調査中 */
    }

    @Test(expected = UserNotFoundException.class)
    public void authenticateInvalidPasswordSuccess(){
        loginService.authenticate(user.getUsername(),"invalidPassword");
    }

    @Test(expected = UserNotFoundException.class)
    public void authenticateInvalidUserSuccess(){
        loginService.authenticate("invalidUser",user.getPassword());
    }
}
