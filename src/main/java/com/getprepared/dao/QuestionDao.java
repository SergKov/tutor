package com.getprepared.dao;

import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionDao {

    void save(Question question);

    Question findById(Long id) throws EntityNotFoundException;

    List<Question> createNewQuiz(Long quizId);

    List<Question> findByQuizId(Long quizId);

    void removeById(Long id) throws EntityNotFoundException;

    void removeByQuizId(Long quizId);
}
