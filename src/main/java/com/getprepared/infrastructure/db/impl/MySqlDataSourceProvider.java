package com.getprepared.infrastructure.db.impl;

import com.getprepared.infrastructure.db.DataSourceProvider;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

/**
 * Created by koval on 04.01.2017.
 */
public class MySqlDataSourceProvider implements DataSourceProvider {

    private static final DataSourceProvider instance = new MySqlDataSourceProvider();

    public static DataSourceProvider getInstance() {
        return instance;
    }

    public DataSource getDataSource() {
        final MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/tutor_test");
        ds.setUser("root");
        ds.setPassword("root");
        return ds;
    }
}
