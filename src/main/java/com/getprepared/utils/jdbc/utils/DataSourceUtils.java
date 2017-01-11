package com.getprepared.utils.jdbc.utils;

import javax.sql.DataSource;
import java.sql.Connection;

import static com.getprepared.utils.jdbc.function.DataSourceCallback.call;

/**
 * Created by koval on 08.01.2017.
 */
public class DataSourceUtils {

    private DataSourceUtils() { }

    public static Connection getConnection(final DataSource ds)  {
        return call(ds::getConnection, "Failed to get connection");
    }
}
