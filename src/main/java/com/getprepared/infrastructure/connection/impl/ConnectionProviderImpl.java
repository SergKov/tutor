package com.getprepared.infrastructure.connection.impl;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.utils.ConnectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public class ConnectionProviderImpl implements ConnectionProvider {

    private DataSource ds;

    public ConnectionProviderImpl() { }

    public ConnectionProviderImpl(DataSource dataSource) {
        this.ds = dataSource;
    }

    @Override
    public Connection getConnection() {
        return ConnectionUtils.getConnection(ds);
    }

    public DataSource getDataSource() {
        return ds;
    }

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }
}
