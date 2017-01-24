package com.getprepared.dao;

import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionDao {

    void save(Question question) throws EntityExistsException;

    Question findById(Long id) throws EntityNotFoundException;

    List<Question> findByQuizId(Long quizId) throws EntityNotFoundException;

    void removeById(Long id) throws EntityNotFoundException;

    void removeByQuizId(Long quizId) throws EntityNotFoundException;
}
