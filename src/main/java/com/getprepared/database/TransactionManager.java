package com.getprepared.database;

import com.getprepared.annotation.Inject;

/**
 * Created by koval on 05.01.2017.
 */
public class TransactionManager {

    private static final TransactionManager instance = new TransactionManager();

    public static TransactionManager getInstance() {
        return instance;
    }

    @Inject
    private TransactionalConnectionProvider provider;

    public void begin() {
        provider.begin();
    }

    public void commit() {
        provider.commit();
    }

    public void rollback() {
        provider.rollback();
    }
}