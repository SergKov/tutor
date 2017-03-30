package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;

import java.util.List;

/**
 * Created by koval on 15.01.2017.
 */
@Service("resultService")
public class ResultServiceImpl extends AbstractService implements ResultService {

    @Inject
    private ResultDao resultDao;

    @Inject
    private UserService userService;

    @Inject
    private QuizService quizService;

    @Override
    public void save(final Result result) throws EntityExistsException {
        try {
            transactionManager.begin();
            resultDao.save(result);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Result> findByUserId(final Long id) { // TODO
        return null;
    }

    @Override
    public List<Result> findByQuizId(final Long id, final PageableData page) { // TODO
        return null;
    }
}