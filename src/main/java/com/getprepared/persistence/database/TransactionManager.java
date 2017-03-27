package com.getprepared.persistence.database;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;

/**
 * Created by koval on 05.01.2017.
 */
@Component
public class TransactionManager {

    @Inject
    private ConnectionProvider connectionProvider;

    public void begin() {
        connectionProvider.begin();
    }

    public void commit() {
        connectionProvider.commit();
    }

    public void rollback() {
        connectionProvider.rollback();
    }
}