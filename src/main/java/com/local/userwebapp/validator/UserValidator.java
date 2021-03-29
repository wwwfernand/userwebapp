package com.local.userwebapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.local.userwebapp.model.User;
import com.local.userwebapp.service.UserServiceImpl;

/**
 * ユーザーバリデーション
 */
@Component
public class UserValidator implements Validator {
    
    /** The user service. */
    @Autowired
    private UserServiceImpl userService;

    /**
     * Supports.
     *
     * @param aClass the a class
     * @return true, if successful
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * ユーザーバリデーション
     *
     * @param obj the obj
     * @param errors the errors
     */
    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }
}
