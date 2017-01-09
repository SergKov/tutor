package com.getprepared.service;

import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuestionService {

    void save(Question question);

    Question findById(Long id);

    List<Question> findByQuizId(Long id);

    Map<Question, Set<Answer>> createNewQuiz(Long quizId);

    void update(Question question);

    void remove(Question question);
}
