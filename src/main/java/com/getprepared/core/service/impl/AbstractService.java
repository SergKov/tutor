package com.getprepared.core.service.impl;

import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.persistence.domain.Quiz;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    protected void checkActive(final Quiz quiz) throws QuizTerminatedException {
        if (quiz.getActive()) {
            throw new QuizTerminatedException(String.format("Quiz %s is active.", quiz));
        }
    }
}
