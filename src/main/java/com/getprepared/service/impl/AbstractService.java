package com.getprepared.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.database.TransactionManager;
import com.getprepared.util.Validation;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    @Inject
    private TransactionManager tm;

    @Inject
    private Validation validation;

    public AbstractService() { }

    protected TransactionManager getTransactionManager() {
        return tm;
    }
}
