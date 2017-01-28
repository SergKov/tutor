package com.getprepared.utils.jdbc.function;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by koval on 08.01.2017.
 */
@FunctionalInterface
public interface SqlCallback<E> {

    Logger LOG = Logger.getLogger(SqlCallback.class);

    E call() throws SQLException;

    static <E> E call(final SqlCallback<E> callback, final String errorMsg) {
        try {
            return callback.call();
        } catch (final SQLException e) {
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }
}
