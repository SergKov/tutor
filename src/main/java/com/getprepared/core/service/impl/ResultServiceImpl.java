package com.getprepared.core.service.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;

import java.util.List;

/**
 * Created by koval on 15.01.2017.
 */
@Component("resultService")
public class ResultServiceImpl extends AbstractService implements ResultService {

    @Inject
    private ResultDao resultDao;

    @Inject
    private UserService userService;

    @Inject
    private QuizService quizService;

    public ResultServiceImpl() { }

    @Override
    public void save(final Result result) throws EntityExistsException {
        try {
            resultDao.save(result);
        } catch (final EntityExistsException e) {
            throw e;
        }
    }

    @Override //TODO
    public Result findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final Result result = resultDao.findById(id);
            final User user = userService.findById(result.getId());
            final Quiz quiz = quizService.findById(result.getId());
            transactionManager.rollback();
            result.setQuiz(quiz);
            result.setUser(user);
            return result;
        } catch (final EntityNotFoundException e) {
            throw e;
        }
    }

    @Override // TODO
    public List<Result> findByUserId(final Long userId) {
        transactionManager.begin();
        final List<Result> result = resultDao.findByUserId(userId);
        transactionManager.commit();
        return result;
    }
}