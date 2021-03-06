package com.getprepared.core.service;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.persistence.domain.Question;
import com.getprepared.web.dto.TestQuestion;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuestionService {

    void save(Question question) throws EntityExistsException;

    Question findById(Long id) throws EntityNotFoundException;

    List<Question> findByQuizId(Long id);

    List<Question> findByQuizIdInRandomOrder(Long id);

    void remove(Long id) throws EntityNotFoundException, QuizTerminatedException;

    List<TestQuestion> startTest(Long quizId);

    double endTest(Long quizId, Long userId, List<TestQuestion> test);
}
