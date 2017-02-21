package com.getprepared.service;

import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

/**
 * Created by koval on 09.01.2017.
 */
public interface UserService {

    User findById(Long id) throws EntityNotFoundException;

    User signInStudent(String email, String password) throws EntityNotFoundException;

    User signInTutor(String email, String password) throws EntityNotFoundException;

    void signUp(User user) throws EntityExistsException;
}
