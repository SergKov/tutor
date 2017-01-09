package com.getprepared.dao;

import com.getprepared.domain.ChosenAnswer;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface ChosenAnswerDao {

    void save(ChosenAnswer answer);

    List<ChosenAnswer> findByQuestionId(Long questionId);
}
