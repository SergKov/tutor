package com.getprepared.utils.jdbc.function;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by koval on 09.01.2017.
 */
@FunctionalInterface
public interface DataSourceCallback<E> {

    Logger LOG = Logger.getLogger(DataSourceCallback.class);

    E call() throws SQLException;

    static <E> E call(final DataSourceCallback<E> callback, final String errorMsg) {
        try {
            return callback.call();
        } catch (final SQLException e) {
            LOG.error(errorMsg, e);
            throw new IllegalStateException(e);
        }
    }
}
