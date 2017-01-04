package com.getprepared.dao;

import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionDao {

    void save(Question question);

    Question findById(Long id);

    List<Question> createNewQuiz(Long quizId);

    List<Question> findByQuizId(Long quizId);

    void update(Question question);

    void removeById(Long id);

    void removeByQuizId(Long quizId);
}
