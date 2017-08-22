package com.ksulima.security.service.user;

import com.ksulima.security.database.User;
import com.ksulima.security.database.UserRepository;
import com.ksulima.security.model.UserCreateForm;
import com.ksulima.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
}
