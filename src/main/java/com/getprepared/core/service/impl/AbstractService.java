package com.getprepared.core.service.impl;

import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;

import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_NUMBER_OF_ELEMENTS;
import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_PAGE_NUMBER;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    protected void checkActive(final Quiz quiz) throws QuizTerminatedException {
        if (quiz.getActive()) {
            throw new QuizTerminatedException(String.format("Quiz %s is active.", quiz));
        }
    }

    protected void checkPage(final PageableData page) {
        if (page.getShowElements() < 0 || page.getCurrentPage() < 0 ||
                page.getNumberOfElements() < (page.getCurrentPage() - 1) * page.getShowElements()) {
            page.setCurrentPage(DEFAULT_PAGE_NUMBER);
            page.setNumberOfElements(DEFAULT_NUMBER_OF_ELEMENTS);
        }
    }
}
