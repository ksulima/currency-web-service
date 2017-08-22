package com.ksulima.security.service;

import com.ksulima.security.database.User;
import com.ksulima.security.model.UserCreateForm;

import java.util.Collection;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */

public interface UserService {

    User findById(Long id);
    User findByEmail(String email);
    Collection<User> getAllUsers();
    User create(UserCreateForm form);
}
