package com.getprepared.utils;

import com.getprepared.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by koval on 08.01.2017.
 */
@FunctionalInterface
public interface SqlRunnable<E> {

    Logger LOG = Logger.getLogger(SqlRunnable.class);

    void run() throws SQLException;

    static <E> void run(final SqlRunnable<E> runnable, final String errorMsg) {
        try {
            runnable.run();
        } catch (final SQLException e) {
            LOG.error(errorMsg, e);
            throw new DataAccessException(e);
        }
    }
}
