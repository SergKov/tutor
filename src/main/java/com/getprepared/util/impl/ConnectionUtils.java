package com.getprepared.util.impl;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import static com.getprepared.util.impl.ConnectionUtils.SqlRunner.run;

/**
 * Created by koval on 05.01.2017.
 */
public class ConnectionUtils {

    private static final Logger LOG = Logger.getLogger(ConnectionUtils.class);

    private ConnectionUtils() { }

    public static void commit(final Connection con) {
        run(con::commit, "Failed to commit connection");
    }

    public static void rollback(final Connection con) {
        run(con::rollback, "Failed to rollback connection");
    }

    public static void setAutoCommit(final Connection con, final boolean autoCommit) {
        run(() -> con.setAutoCommit(autoCommit), "Failed to set auto commit");
    }

    public static void close(final Connection con) {
        run(con::close, "Failed to close connection");
    }

    @FunctionalInterface
    public interface SqlRunner<E> {

        void run() throws SQLException;

        static <E> void run(final SqlRunner<E> runnable, final String errorMsg) {
            try {
                runnable.run();
            } catch (final SQLException e) {
                LOG.error(errorMsg, e);
                throw new IllegalStateException(errorMsg, e);
            }
        }
    }
}
