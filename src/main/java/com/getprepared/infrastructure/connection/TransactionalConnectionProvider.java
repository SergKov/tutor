package com.getprepared.infrastructure.connection;

import com.getprepared.exception.TransactionalException;
import com.getprepared.infrastructure.data_source.DataSourceFactory;
import com.getprepared.utils.jdbc.utils.ConnectionUtils;
import com.getprepared.utils.jdbc.utils.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public class TransactionalConnectionProvider {

    private static final TransactionalConnectionProvider instance = new TransactionalConnectionProvider();

    public static TransactionalConnectionProvider getInstance() {
        return instance;
    }

    private final DataSource ds = DataSourceFactory.getInstance().getDataSource();

    private final ThreadLocal<TransactionConnectionCounter> threadLocal = new ThreadLocal<>();

    public void begin() {

        if (threadLocal.get() == null) {
            final Connection con = DataSourceUtils.getConnection(ds);
            incrementAndSet(con);
        }

        threadLocal.get().increment();
    }

    public void commit() {

        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        if (threadLocal.get().isZero()) {
            final Connection con = threadLocal.get().getConnection();
            ConnectionUtils.commit(con);
            ConnectionUtils.close(con);
            threadLocal.remove();
        } else {
            threadLocal.get().decrement();
        }
    }

    public void rollback() {

        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        final Connection con = threadLocal.get().getConnection();
        ConnectionUtils.rollback(con);
        ConnectionUtils.close(con);
        threadLocal.remove();
    }

    public boolean isNew() {
        return threadLocal.get() == null;
    }

    public Connection getConnection() {

        if (threadLocal.get() == null) {
            final Connection con = DataSourceUtils.getConnection(ds);
            incrementAndSet(con);
            return con;
        }

        return threadLocal.get().getConnection();
    }

    private void incrementAndSet(final Connection con) {
        ConnectionUtils.setAutoCommit(con, false);
        final TransactionConnectionCounter transactionConnectionCounter = new TransactionConnectionCounter();
        transactionConnectionCounter.setConnection(con);
        transactionConnectionCounter.increment();
        threadLocal.set(transactionConnectionCounter);
    }
}
