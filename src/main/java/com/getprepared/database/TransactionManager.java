package com.getprepared.database;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;

/**
 * Created by koval on 05.01.2017.
 */
@Bean("transactionManager")
public class TransactionManager {

    private static final TransactionManager instance = new TransactionManager();

    public static TransactionManager getInstance() {
        return instance;
    }

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