package com.getprepared.database;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.exception.TransactionalException;
import com.getprepared.util.jdbc.util.ConnectionUtils;
import com.getprepared.util.jdbc.util.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
@Bean("transactionalConnectionProvider")
public class TransactionalConnectionProvider {

    @Inject
    private DataSource dataSource;

    private final ThreadLocal<TransactionConnectionCounter> threadLocal = new ThreadLocal<>();

    public void begin() {

        if (threadLocal.get() == null) {
            create();
        }

        threadLocal.get().increment();
    }

    private void create() {
        final Connection con = DataSourceUtils.getConnection(dataSource);
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
}
