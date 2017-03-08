package com.getprepared.database;

import com.getprepared.annotation.Inject;

/**
 * Created by koval on 05.01.2017.
 */
public class TransactionManager {

    @Inject
    private TransactionalConnectionProvider transactionalConnectionProvider;

    public void begin() {
        transactionalConnectionProvider.begin();
    }

    public void commit() {
        transactionalConnectionProvider.commit();
    }

    public void rollback() {
        transactionalConnectionProvider.rollback();
    }
}