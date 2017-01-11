package com.getprepared.infrastructure.connection.impl;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.utils.jdbc.utils.ConnectionUtils;
import com.getprepared.utils.jdbc.utils.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public class TransactionalConnectionProvider extends ConnectionProvider { //TODO

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public TransactionalConnectionProvider(DataSource dataSource) {
        super(dataSource);
    }

    public void begin() {
        if (isNew()) {
            final Connection con = DataSourceUtils.getConnection(dataSource);
            threadLocal.set(con);
        }
    }

    public void commit() {
        final Connection con = threadLocal.get();
        ConnectionUtils.commit(con);
        ConnectionUtils.close(con);
        threadLocal.remove();
    }

    public void rollback() {
        final Connection con = threadLocal.get();
        ConnectionUtils.rollback(con);
        ConnectionUtils.close(con);
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
