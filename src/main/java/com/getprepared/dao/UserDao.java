package com.getprepared.dao;

import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

/**
 * Created by koval on 03.01.2017.
 */
public interface UserDao {

    void save(User user) throws EntityExistsException;

    User findById(Long id) throws EntityNotFoundException;

    User findByCredentials(String email, String password) throws EntityNotFoundException;

    User findByTutorCredentials(String email, String password) throws EntityNotFoundException;

    User findByEmail(String email) throws EntityNotFoundException;

    void update(User user) throws EntityExistsException;

    void remove(Long userId, Long quizId) throws EntityNotFoundException;
}
