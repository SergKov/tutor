package com.getprepared.infrastructure.tm;

import com.getprepared.infrastructure.connection.TransactionalConnectionProvider;

/**
 * Created by koval on 05.01.2017.
 */
public class TransactionManager {

    private static final TransactionManager instance = new TransactionManager();

    public static TransactionManager getInstance() {
        return instance;
    }

    private final TransactionalConnectionProvider provider = getTransactionConnectionProvider();

    public void begin() {
        provider.begin();
    }

    public void commit() {
        provider.commit();
    }

    public void rollback() {
        provider.rollback();
    }

    private TransactionalConnectionProvider getTransactionConnectionProvider() {
        return TransactionalConnectionProvider.getInstance();
    }
}