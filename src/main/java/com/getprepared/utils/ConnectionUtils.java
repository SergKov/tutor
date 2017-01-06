package com.getprepared.utils;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Created by koval on 05.01.2017.
 */
public class ConnectionUtils {

    private static final Logger LOG = Logger.getLogger(ConnectionUtils.class);

    private ConnectionUtils() { }

    @FunctionalInterface
    private interface Callback<E> extends Callable<E> {
        @Override
        E call() throws SQLException;
    }

    private static <E> E call(final Callback<E> callback, final String errorMsg) {
        try {
            return callback.call();
        } catch (final SQLException e) {
            LOG.error(errorMsg, e);
            throw new IllegalStateException(e);
        }
    }

    public static Connection getConnection(final DataSource ds) {
        return call(ds::getConnection, "Failed to get connection");
    }

    public static void commit(final Connection con) {
        call(() -> { con.commit(); return con; } , "Failed to commit connection");
    }

    public static void rollback(final Connection con) {
        call(() -> { con.rollback(); return con; } , "Failed to rollback connection");
    }

    public static void setAutoCommitFalse(final Connection con) {
        call(() -> { con.setAutoCommit(false); return con; }, "Failed to setAutoCommitFalse");
    }

    public static PreparedStatement prepareStatement(final Connection con, final String query) {
        return call(() -> con.prepareStatement(query),  "Failed to get PrepareStatement");
    }


}
