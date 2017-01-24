package com.getprepared.service;

import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.infrastructure.pagination.Page;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz) throws ValidationException, EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findAll();

    List<Quiz> findByUserEmail(String email) throws ValidationException, EntityNotFoundException;

    List<Quiz> findByUserId(Long id) throws EntityNotFoundException;

    void assign(Long quizId, Long userId) throws ValidationException, EntityNotFoundException, EntityExistsException;

    void remove(Quiz quiz) throws EntityNotFoundException;
}
