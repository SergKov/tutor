package com.getprepared.dao;

import com.getprepared.domain.User;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface UserDao {

    void save(User user);

    User findByCredentials(String email, String password);

    User findByEmail(String email);

    List<User> findAllByQuizId(Long quizId);

    void update(User user);
}
