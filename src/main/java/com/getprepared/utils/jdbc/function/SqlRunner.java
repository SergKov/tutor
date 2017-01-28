package com.getprepared.utils.jdbc.function;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by koval on 08.01.2017.
 */
@FunctionalInterface
public interface SqlRunner<E> {

    Logger LOG = Logger.getLogger(SqlRunner.class);

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
