package com.getprepared.dao;

import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface UserDao {

    void save(User user) throws EntityExistsException;

    User findByCredentials(String email, String password) throws EntityNotFoundException;

    User findByEmail(String email) throws EntityNotFoundException;

    List<User> findAllByQuizId(Long quizId) throws EntityNotFoundException;

    List<User> findAll();

    void updateCredentials(String email, String password) throws EntityExistsException;

    void remove(Long userId, Long quizId) throws EntityNotFoundException;
}
