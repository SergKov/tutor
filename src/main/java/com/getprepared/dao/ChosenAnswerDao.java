package com.getprepared.dao;

import com.getprepared.domain.AnswerDTO;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface ChosenAnswerDao {

    void save(ChosenAnswerDao answer);

    List<AnswerDTO> findByQuestionId(Long questionId);
}
