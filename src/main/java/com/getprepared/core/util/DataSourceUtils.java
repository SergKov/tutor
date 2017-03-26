package com.getprepared.core.util;

import com.getprepared.annotation.Component;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by koval on 08.01.2017.
 */
@Component
public final class DataSourceUtils {

    private static final Logger LOG = Logger.getLogger(DataSourceUtils.class);

    public Connection getConnection(final DataSource ds) {
        try {
            return ds.getConnection();
        } catch (final SQLException e) {
            LOG.error("Failed to get connection", e);
            throw new IllegalStateException(e);
        }
    }
}
