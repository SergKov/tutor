package com.getprepared.service.impl;

import com.getprepared.dao.impl.DaoFactory;
import com.getprepared.database.TransactionManager;
import com.getprepared.utils.Validation;
import com.getprepared.utils.factory.UtilsFactory;

import static com.getprepared.constant.UtilsConstant.VALIDATION;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    private DaoFactory daoFactory;

    private TransactionManager tm;

    private Validation validation;

    public AbstractService() { }

    public void init() {
        daoFactory = DaoFactory.getInstance();
        tm = TransactionManager.getInstance();
        validation = UtilsFactory.getInstance().getUtil(VALIDATION, Validation.class);
    }

    protected TransactionManager getTransactionManager() {
        return tm;
    }

    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }

    protected Validation getValidation() {
        return validation;
    }
}
