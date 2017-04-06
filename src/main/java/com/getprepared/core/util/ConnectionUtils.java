package com.getprepared.core.util;

import com.getprepared.annotation.Component;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import static com.getprepared.core.util.ConnectionUtils.SqlRunner.run;

/**
 * Created by koval on 05.01.2017.
 */
@Component
public final class ConnectionUtils {

    private static final Logger LOG = Logger.getLogger(ConnectionUtils.class);

    public void commit(final Connection con) {
        run(con::commit, "Failed to commit connection");
    }

    public void rollback(final Connection con) {
        run(con::rollback, "Failed to rollback connection");
    }

    public void setAutoCommit(final Connection con, final boolean autoCommit) {
        run(() -> con.setAutoCommit(autoCommit), "Failed to set auto commit");
    }

    public void close(final Connection con) {
        run(con::close, "Failed to close connection");
    }

    @FunctionalInterface
    public interface SqlRunner {

        void run() throws SQLException;

        static void run(final SqlRunner runnable, final String errorMsg) {
            try {
                runnable.run();
            } catch (final SQLException e) {
                LOG.error(errorMsg, e);
                throw new IllegalStateException(errorMsg, e);
            }
        }
    }
}
