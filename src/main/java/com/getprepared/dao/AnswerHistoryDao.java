package com.getprepared.dao;

import com.getprepared.domain.AnswerHistory;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface AnswerHistoryDao {

    void save(AnswerHistory answer);

    List<AnswerHistory> findByQuestionId(Long questionId);
}
