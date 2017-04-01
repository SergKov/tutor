package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.persistence.database.TransactionManager;
import com.getprepared.persistence.domain.Quiz;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    @Inject
    protected TransactionManager transactionManager;

    protected void checkActive(final Quiz quiz) throws QuizTerminatedException {
        if (quiz.isActive()) {
            throw new QuizTerminatedException(String.format("Quiz %s is active.", quiz));
        }
    }
}
