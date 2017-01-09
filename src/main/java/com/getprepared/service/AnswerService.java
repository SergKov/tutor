package com.getprepared.service;

import com.getprepared.domain.Answer;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface AnswerService {

    void save(Answer answer);

    Answer findById(Long id);

    List<Answer> findByQuestionId(Long questionId);

    void remove(Answer answer);
}
