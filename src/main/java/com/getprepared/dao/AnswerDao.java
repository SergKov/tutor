package com.getprepared.dao;

import com.getprepared.domain.AnswerDTO;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface AnswerDao {

    void save(AnswerDTO answer);

    AnswerDTO findById(Long id);

    List<AnswerDTO> findByQuestionId(Long questionId);

    void removeById(Long id);

    void removeByQuestionId(Long questionId);
}
