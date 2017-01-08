package com.getprepared.infrastructure.connection.impl;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.utils.ConnectionUtils;
import com.getprepared.utils.DataSourceUtils;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public class TransactionalConnectionProvider implements ConnectionProvider {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DataSource ds; //TODO

    public TransactionalConnectionProvider() {
        this.ds = new MysqlDataSource();
    }

    public TransactionalConnectionProvider(DataSource dataSource) {
        this.ds = dataSource;
    }

    public void begin() {
        if (isNew()) {
            final Connection con = DataSourceUtils.getConnection(ds);
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
            final Connection con = DataSourceUtils.getConnection(ds);
            threadLocal.set(con);
            return con;
        }

        return threadLocal.get();
    }


    public DataSource getDataSource() {
        return ds;
    }

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }
}
