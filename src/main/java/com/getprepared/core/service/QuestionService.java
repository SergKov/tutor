package com.getprepared.core.service;

import com.getprepared.web.form.TestQuestion;
import com.getprepared.persistence.domain.Question;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;

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
