package com.getprepared.service;

import com.getprepared.domain.QuestionHistory;

/**
 * Created by koval on 09.01.2017.
 */
public interface QuestionHistoryService {

    void save(QuestionHistory questionHistory);

    QuestionHistory findById(Long id);
}
