package com.getprepared.dao;

import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuizDao {

    void save(Quiz quiz);

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findByUserId(Long id);

    List<Quiz> findByUserEmail(String email);

    List<Quiz> findAll();

    void updateTime(LocalTime time);

    void removeById(Long id);
}
