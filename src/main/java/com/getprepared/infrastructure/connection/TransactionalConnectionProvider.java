package com.getprepared.infrastructure.connection;

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

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public void begin() {
        if (isNew()) {
            final Connection con = DataSourceUtils.getConnection(ds);
            ConnectionUtils.setAutoCommit(con, false);
            threadLocal.set(con);
        }
    }

    public void commit() {
        final Connection con = threadLocal.get();
        ConnectionUtils.commit(con);
    }

    public void rollback() {
        final Connection con = threadLocal.get();
        ConnectionUtils.rollback(con);
        end();
    }

    public void end() {
        final Connection con = threadLocal.get();
        ConnectionUtils.close(con);
        threadLocal.remove();
    }

    public boolean isNew() {
        return threadLocal.get() == null;
    }

    public Connection getConnection() {
        if (threadLocal.get() == null) {
            final Connection con = DataSourceUtils.getConnection(ds);
            threadLocal.set(con);
            return con;
        }
        return threadLocal.get();
    }
}
