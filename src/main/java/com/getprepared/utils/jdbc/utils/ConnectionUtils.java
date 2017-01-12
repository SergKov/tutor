package com.getprepared.utils.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.getprepared.utils.jdbc.function.SqlCallback.call;
import static com.getprepared.utils.jdbc.function.SqlRunner.run;

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
        run(() -> con.setAutoCommit(autoCommit) , "Failed to setAutoCommitFalse");
    }

    public static void close(final Connection con) {
        run(con::close, "Failed to close");
    }
}
