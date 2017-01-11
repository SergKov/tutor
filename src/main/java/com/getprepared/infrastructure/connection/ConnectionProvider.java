package com.getprepared.infrastructure.connection;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by koval on 02.01.2017.
 */
public abstract class ConnectionProvider {

    protected final DataSource dataSource;

    public ConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract Connection getConnection();

    public DataSource getDataSource() {
        return dataSource;
    }
}
