package com.getprepared.dao;

import com.getprepared.domain.QuestionHistory;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuestionHistoryDao {

    void save(QuestionHistory question);

    List<QuestionHistory> findByResultId(Long resultId);
}
