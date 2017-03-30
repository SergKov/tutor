package com.getprepared.core.service;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.Quiz;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findAll();

    List<Quiz> findAllActive();

    void remove(Quiz quiz) throws EntityNotFoundException;
}
