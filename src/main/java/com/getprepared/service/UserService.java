package com.getprepared.service;

import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;

/**
 * Created by koval on 09.01.2017.
 */
public interface UserService {

    User findById(Long id) throws ValidationException, EntityNotFoundException;

    User findByEmail(String email) throws ValidationException, EntityNotFoundException;

    User signIn(String email, String password) throws EntityNotFoundException;

    User signInTutor(String email, String password) throws EntityNotFoundException;

    void signUp(User user) throws EntityExistsException;

    void update(User user) throws EntityExistsException;
}
