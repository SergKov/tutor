package com.getprepared.service;

import com.getprepared.domain.ChosenAnswer;

/**
 * Created by koval on 09.01.2017.
 */
public interface ChosenAnswerService {

    void save(ChosenAnswer answer);

    ChosenAnswer findById(Long id);
}
