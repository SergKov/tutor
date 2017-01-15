package com.getprepared.service;

import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz) throws ValidationException, EntityExistsException;

    Quiz findById(Long id) throws ValidationException, EntityNotFoundException;

    List<Quiz> findAll();

    List<Quiz> findAllByUserEmail(String email) throws ValidationException, EntityNotFoundException;

    List<Quiz> findAllByUserId(Long id) throws ValidationException, EntityNotFoundException;

    void institute(Long id, User user);

    void remove(Quiz quiz) throws ValidationException, EntityNotFoundException;
}
