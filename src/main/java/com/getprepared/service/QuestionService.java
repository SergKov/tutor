package com.getprepared.service;

import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuestionService {

    void save(Question question) throws EntityExistsException;

    Question findById(Long id) throws EntityNotFoundException;

    List<Question> findByQuizId(Long id);

    void remove(Question question) throws EntityNotFoundException;

    List<TestQuestion> startTest(Long quizId);

    double endTest(List<TestQuestion> test);
}
