package com.getprepared.core.util;

import com.getprepared.annotation.Component;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.getprepared.core.util.DataSourceUtils.DataSourceCallback.call;

/**
 * Created by koval on 08.01.2017.
 */
@Component
public final class DataSourceUtils {

    private static final Logger LOG = Logger.getLogger(DataSourceUtils.class);

    public Connection getConnection(final DataSource ds)  {
        return call(ds::getConnection, "Failed to get connection");
    }

    @FunctionalInterface
    public interface DataSourceCallback<E> {

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
}
