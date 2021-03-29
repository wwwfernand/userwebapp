package com.local.userwebapp.test;

import com.local.userwebapp.config.TestDbConfig;
import com.local.userwebapp.config.TestIntegrationConfig;
import com.local.userwebapp.error.UserAlreadyExistException;
import com.local.userwebapp.model.User;
import com.local.userwebapp.service.UserService;
import com.local.userwebapp.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDbConfig.class, TestIntegrationConfig.class })
public class UserServiceIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void registerNonExistSave(){
        final String userName=UUID.randomUUID().toString();
        final User user = new User();
        user.setUsername(userName);
        user.setPassword(UUID.randomUUID().toString());
        final User savedUser = userService.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getUsername());
        assertEquals(userName, savedUser.getUsername());
        assertNotNull(savedUser.getId());
    }

    @Test(expected = UserAlreadyExistException.class)
    public void registerExistingError(){
        final User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        userService.save(user);
        userService.save(user);
    }
}
