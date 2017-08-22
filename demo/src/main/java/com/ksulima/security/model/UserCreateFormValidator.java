package com.ksulima.security.model;

import com.ksulima.security.service.UserService;
import com.ksulima.util.EmailAlreadyExists;
import com.ksulima.util.NotMatchedPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
// */
@Component
public class UserCreateFormValidator{

    @Autowired
    private UserService userService;



    public void validate(UserCreateForm form) throws Exception {
        validatePasswords(form);
        validateEmail(form);
    }

    private void validatePasswords(UserCreateForm form) throws Exception{
        if(!form.getPassword().equals(form.getPasswordRepeated())){
            throw new NotMatchedPassword("Repeated password must be the same");

        }
    }

    private void validateEmail(UserCreateForm form) throws Exception {
        if(this.userService.findByEmail(form.getEmail()) != null){
            throw new EmailAlreadyExists("User with this email already exists");
        }
    }
}
