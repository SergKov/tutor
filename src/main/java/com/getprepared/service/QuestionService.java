package com.getprepared.service;

import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuestionService {

    void save(Question question) throws ValidationException, EntityExistsException;

    Question findById(Long id) throws ValidationException, EntityNotFoundException;

    List<Question> findByQuizId(Long id) throws EntityNotFoundException;

    void remove(Question question) throws EntityNotFoundException;

    void removeByQuizId(Long quizId) throws EntityNotFoundException;
}
