package com.getprepared.util.jdbc.util;

import java.sql.Connection;

import static com.getprepared.util.jdbc.function.SqlRunner.run;

/**
 * Created by koval on 05.01.2017.
 */
public class ConnectionUtils {

    private ConnectionUtils() { }

    public static void commit(final Connection con) {
        run(con::commit, "Failed to commit connection");
    }

    public static void rollback(final Connection con) {
        run(con::rollback , "Failed to rollback connection");
    }

    public static void setAutoCommit(final Connection con, final boolean autoCommit) {
        run(() -> con.setAutoCommit(autoCommit) , "Failed to set auto commit");
    }

    public static void close(final Connection con) {
        run(con::close, "Failed to close connection");
    }
}
