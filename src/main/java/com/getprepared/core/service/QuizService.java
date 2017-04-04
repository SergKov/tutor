package com.getprepared.core.service;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizNotTerminatedException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findAllByTutorId(Long id, PageableData pageableData) throws EntityNotFoundException;

    List<Quiz> findAllActive(PageableData pageableData) throws EntityNotFoundException;

    void active(Long id) throws QuizTerminatedException, EntityNotFoundException, QuizNotTerminatedException;

    void update(Quiz quiz) throws QuizTerminatedException, EntityExistsException, EntityNotFoundException;

    void remove(Long id);
}
