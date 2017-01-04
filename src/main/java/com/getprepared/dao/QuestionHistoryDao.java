package com.getprepared.dao;

import com.getprepared.domain.QuestionHistoryDTO;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionHistoryDao {

    void save(QuestionHistoryDTO question);

    List<QuestionHistoryDTO> findByResultId(Long resultId);
}
