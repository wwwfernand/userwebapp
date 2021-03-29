package com.local.userwebapp.test;

import com.local.userwebapp.config.TestDbConfig;
import com.local.userwebapp.config.TestIntegrationConfig;
import com.local.userwebapp.error.UserAlreadyExistException;
import com.local.userwebapp.model.User;
import com.local.userwebapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDbConfig.class, TestIntegrationConfig.class})
@Transactional
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setup() throws UserAlreadyExistException {
        user = new User();
        user.setUsername("validUser");
        user.setPassword("validPassword");
        userRepository.save(user);
    }

    @Test
    public void saveUserSuccess() {
        assertTrue(userRepository.count() > 0);
    }

    @Test
    public void findValidUserFound() {
        assertTrue(userRepository.findByUsername(user.getUsername()) != null);
    }

    @Test
    public void findInValidUserNotFound() {
        assertTrue(userRepository.findByUsername("InvalidUser") == null);
    }
}
