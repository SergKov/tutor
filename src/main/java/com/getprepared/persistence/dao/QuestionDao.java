package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.Question;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionDao {

    void save(Question question) throws EntityExistsException;

    Question findById(Long id) throws EntityNotFoundException;

    List<Question> findByQuizId(Long quizId);

    List<Question> findByQuizIdRandom(Long quizId);

    void update(Question question) throws EntityExistsException;

    void removeById(Long id) throws EntityNotFoundException;
}
