package com.getprepared.service;

import com.getprepared.domain.User;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    User signIn(String email, String password);

    void signUp(User user);

    void update(User user);
}
