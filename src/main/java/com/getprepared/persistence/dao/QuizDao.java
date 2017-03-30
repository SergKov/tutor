package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuizDao {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findAllByTutorId(Long id, PageableData page);

    List<Quiz> findAllCreated(PageableData page);

    void update(Quiz quiz) throws EntityExistsException;

    void activeQuiz(Quiz quiz);

    void remove(Long id) throws EntityNotFoundException;
}
