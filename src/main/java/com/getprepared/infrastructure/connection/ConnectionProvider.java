package com.getprepared.infrastructure.connection;

import com.getprepared.infrastructure.data_source.impl.MySqlDataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 02.01.2017.
 */
public abstract class ConnectionProvider {

    protected DataSource dataSource;

    public void init() {
        dataSource = MySqlDataSourceProvider.getInstance().getDataSource();
    }

    public ConnectionProvider() {
        init();
    }

    public abstract Connection getConnection();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource ds) {
        this.dataSource = dataSource;
    }
}
