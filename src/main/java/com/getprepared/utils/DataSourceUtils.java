package com.getprepared.utils;

import com.getprepared.infrastructure.connection.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;

import static com.getprepared.utils.SqlCallable.call;

/**
 * Created by koval on 08.01.2017.
 */
public class DataSourceUtils {

    private DataSourceUtils() { }

    public static Connection getConnection(final DataSource ds) {
        return call(ds::getConnection, "Failed to get connection");
    }

    public static Connection getConnection(final ConnectionProvider provider) {
        return call(provider::getConnection , "Failed to get connection");
    }
}
