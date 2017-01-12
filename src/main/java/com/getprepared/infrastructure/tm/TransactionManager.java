package com.getprepared.infrastructure.tm;

import com.getprepared.infrastructure.connection.TransactionalConnectionProvider;

/**
 * Created by koval on 05.01.2017.
 */
public class TransactionManager {

    private final TransactionalConnectionProvider provider = TransactionalConnectionProvider.getInstance();

    public void begin() {
        provider.begin();
    }

    public void commit() {
        provider.commit();
    }

    public void rollback() {
        provider.rollback();
    }

    public boolean isNew() {
        return provider.isNew();
    }
}