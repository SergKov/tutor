package com.getprepared.database;

import com.getprepared.exception.TransactionalException;
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

    private DataSource dataSource = DataSourceFactory.getInstance().getDataSource();

    private final ThreadLocal<TransactionConnectionCounter> threadLocal = new ThreadLocal<>();

    public void begin() {

        if (threadLocal.get() == null) {
            create();
        }

        threadLocal.get().increment();
    }

    private void create() {
        final Connection con = DataSourceUtils.getConnection(getDataSource());
        ConnectionUtils.setAutoCommit(con, false);
        final TransactionConnectionCounter transactionConnectionCounter = new TransactionConnectionCounter();
        transactionConnectionCounter.setConnection(con);
        threadLocal.set(transactionConnectionCounter);
    }

    public void commit() {

        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        threadLocal.get().decrement();
        if (threadLocal.get().isZero()) {
            final Connection con = threadLocal.get().getConnection();
            ConnectionUtils.commit(con);
            ConnectionUtils.close(con);
            threadLocal.remove();
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

    public Connection getConnection() {

        if (threadLocal.get() == null) {
            throw new TransactionalException();
        }

        return threadLocal.get().getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
