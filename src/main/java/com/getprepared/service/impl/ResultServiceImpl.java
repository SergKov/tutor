package com.getprepared.service.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.ResultService;

import java.util.List;

/**
 * Created by koval on 15.01.2017.
 */
@Bean("resultService")
public class ResultServiceImpl extends AbstractService implements ResultService {

    @Inject
    private ResultDao resultDao;

    public ResultServiceImpl() { }

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
    public Result findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final Result result = resultDao.findById(id);
            transactionManager.commit();
            return result;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Result> findByUserId(final Long userId) {
        transactionManager.begin();
        final List<Result> result = resultDao.findByUserId(userId);
        transactionManager.commit();
        return result;
    }
}