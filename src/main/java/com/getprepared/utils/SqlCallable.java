package com.getprepared.utils;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Created by koval on 08.01.2017.
 */
@FunctionalInterface
public interface SqlCallable<E> {

    Logger LOG = Logger.getLogger(SqlCallable.class);

    E call() throws SQLException;

    static <E> E call(final SqlCallable<E> callback, final String errorMsg) {
        try {
            return callback.call();
        } catch (final SQLException e) {
            LOG.error(errorMsg, e);
            throw new IllegalStateException(e);
        }
    }
}
