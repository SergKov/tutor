package com.getprepared.infrastructure.tm;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.ConnectionProviderImpl;
import com.getprepared.utils.ConnectionUtils;

import java.sql.Connection;

/**
 * Created by koval on 05.01.2017.
 */
public class TransactionManager {

    private final ConnectionProvider provider = new ConnectionProviderImpl();
    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public void begin() {
        if (threadLocal.get() == null) {
            final Connection con = provider.getConnection();
            ConnectionUtils.setAutoCommitFalse(con);
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
}