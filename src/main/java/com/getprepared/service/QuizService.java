package com.getprepared.service;

import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findAll();

    List<Quiz> findAllCreated();

    void remove(Quiz quiz) throws EntityNotFoundException;
}
