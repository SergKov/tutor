package com.getprepared.service.impl;

import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.ResultService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.RESULT_DAO;

/**
 * Created by koval on 15.01.2017.
 */
public class ResultServiceImpl extends AbstractService implements ResultService {

    private static final Logger LOG = Logger.getLogger(ResultServiceImpl.class);

    @Override
    public void save(final Result result) throws ValidationException, EntityExistsException {
        try {
            getValidation().validateResult(result);

            getTransactionManager().begin();
            final ResultDao resultDao = getDao();
            resultDao.save(result);
            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Result findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(id);

            getTransactionManager().begin();
            final ResultDao resultDao = getDao();
            final Result result = resultDao.findById(id);
            getTransactionManager().commit();
            return result;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Result> findByUserId(final Long userId) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(userId);

            getTransactionManager().begin();
            final ResultDao resultDao = getDao();
            final List<Result> result = resultDao.findByUserId(userId);
            getTransactionManager().commit();
            return result;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private ResultDao getDao() {
        return getDaoFactory().getDao(RESULT_DAO, ResultDao.class);
    }
}