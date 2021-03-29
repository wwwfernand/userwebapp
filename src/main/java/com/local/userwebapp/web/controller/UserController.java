package com.local.userwebapp.web.controller;

import com.local.userwebapp.error.UserNotFoundException;
import com.local.userwebapp.model.User;
import com.local.userwebapp.service.LoginServiceImpl;
import com.local.userwebapp.service.UserServiceImpl;
import com.local.userwebapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Class UserController.
 */
@Controller
public class UserController {
    
    /** The user service. */
    @Autowired
    private UserServiceImpl userService;

    /** The login service. */
    @Autowired
    private LoginServiceImpl loginService;

    /** The user validator. */
    @Autowired
    private UserValidator userValidator;

    /**
     * アカウント新規登録画面
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * アカウント新規登録画面
     *
     * @param userForm the user form
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/";
    }

    /**
     * ログイン画面
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String signin(Model model) {
        return "login";
    }

    /**
     * ログイン画面
     *
     * @param userForm the user form
     * @param bindingResult the binding result
     * @param model the model
     * @return the string
     */
    @PostMapping("/")
    public String authenticate(@ModelAttribute("loginForm") User userForm, BindingResult bindingResult, Model model, HttpServletRequest req) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "login";
        }
        try{
            Authentication auth=loginService.authenticate(userForm.getUsername(), userForm.getPassword());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
            return "redirect:/welcome";
        } catch (UserNotFoundException ex){
            model.addAttribute("error", "ログインIDまたはパスワードが正しくありません。");
            return "login";
        }
    }

    /**
     * ログイン成功画面
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }
}
