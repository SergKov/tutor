package com.getprepared.service.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.ResultService;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.RESULT_DAO;

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
            getTransactionManager().begin();
            resultDao.save(result);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final Result result = resultDao.findById(id);
            getTransactionManager().commit();
            return result;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public List<Result> findByUserId(final Long userId) {
        getTransactionManager().begin();
        final List<Result> result = resultDao.findByUserId(userId);
        getTransactionManager().commit();
        return result;
    }
}