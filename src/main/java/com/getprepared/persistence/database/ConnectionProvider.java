package com.getprepared.persistence.database;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.TransactionalException;
import com.getprepared.core.util.ConnectionUtils;
import com.getprepared.core.util.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
@Component
public class ConnectionProvider {

    @Inject
    private DataSource dataSource;

    @Inject
    private DataSourceUtils dataSourceUtils;

    @Inject
    private ConnectionUtils connectionUtils;

    private final ThreadLocal<ConnectionCounter> threadLocal = new ThreadLocal<>();

    public void begin() {
        if (threadLocal.get() == null) {
            create();
        }

        threadLocal.get().increment();
    }

    private void create() {
        final Connection con = dataSourceUtils.getConnection(dataSource);
        connectionUtils.setAutoCommit(con, false);
        final ConnectionCounter connectionCounter = new ConnectionCounter();
        connectionCounter.setConnection(con);
        threadLocal.set(connectionCounter);
    }

    public void commit() {
        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        threadLocal.get().decrement();
        if (threadLocal.get().isZero()) {
            final Connection con = threadLocal.get().getConnection();
            connectionUtils.commit(con);
            connectionUtils.close(con);
            threadLocal.remove();
        }
    }

    public void rollback() {
        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        final Connection con = threadLocal.get().getConnection();
        connectionUtils.rollback(con);
        connectionUtils.close(con);
        threadLocal.remove();
    }

    public Connection getConnection() {
        return threadLocal.get() != null ? threadLocal.get().getConnection() : dataSourceUtils.getConnection(dataSource);
    }
}
