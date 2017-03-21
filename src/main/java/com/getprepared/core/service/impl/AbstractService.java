package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.persistence.database.TransactionManager;

/**
 * Created by koval on 14.01.2017.
 */
public abstract class AbstractService {

    @Inject
    protected TransactionManager transactionManager;
}
