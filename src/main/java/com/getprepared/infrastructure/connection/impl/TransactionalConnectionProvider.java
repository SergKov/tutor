package com.getprepared.infrastructure.connection.impl;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.utils.jdbc.utils.ConnectionUtils;
import com.getprepared.utils.jdbc.utils.DataSourceUtils;

import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public class TransactionalConnectionProvider extends ConnectionProvider {

    private static final TransactionalConnectionProvider instance = new TransactionalConnectionProvider();

    public static TransactionalConnectionProvider getInstance() {
        return instance;
    }

    private TransactionalConnectionProvider() { }

    public void begin() {
        if (isNew()) {
            final Connection con = DataSourceUtils.getConnection(dataSource);
            threadLocal.set(con);
        }
    }

    public void commit() {
        final Connection con = threadLocal.get();
        ConnectionUtils.commit(con);
        threadLocal.remove();
    }

    public void rollback() {
        final Connection con = threadLocal.get();
        ConnectionUtils.rollback(con);
        threadLocal.remove();
    }

    public boolean isNew() {
        return threadLocal.get() == null;
    }

    @Override
    public Connection getConnection() {

        if (threadLocal.get() == null) {
            final Connection con = DataSourceUtils.getConnection(dataSource);
            threadLocal.set(con);
            return con;
        }

        return threadLocal.get();
    }
}
