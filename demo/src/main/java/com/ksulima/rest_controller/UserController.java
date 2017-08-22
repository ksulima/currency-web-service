package com.ksulima.rest_controller;

import com.ksulima.security.database.User;
import com.ksulima.security.model.Role;
import com.ksulima.security.model.UserCreateForm;
import com.ksulima.security.model.UserCreateFormValidator;
import com.ksulima.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/email", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam(value = "email", required = true) String email){
        return userService.findByEmail(email);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable Long id){
        return userService.findById(id);
    }


    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public void createUser(@RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "passwordRepeated", required = true) String passwordRepeated) throws Exception{
        UserCreateForm form = new UserCreateForm();
        form.setEmail(email);
        form.setPassword(password);
        form.setPasswordRepeated(passwordRepeated);
        form.setRole(Role.USER);
        userCreateFormValidator.validate(form);
        this.userService.create(form);

    }
}
