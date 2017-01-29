package com.getprepared.dao;

import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuizDao {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findByUserId(Long id) throws EntityNotFoundException;

    List<Quiz> findAll();

    void remove(Long id) throws EntityNotFoundException;
}
