package com.getprepared.service;

import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuizService {

    void save(Quiz quiz);

    Quiz findById(Long id);

    List<Quiz> findAll();

    List<Quiz> findAllByUserEmail(String email);

    List<Quiz> findAllByUserId(Long id);

    void connectQuiz(Long id, User user);

    void update(Quiz quiz);

    void remove(Quiz quiz);
}
