package com.getprepared.infrastructure.db.impl;

import com.getprepared.infrastructure.db.DataSourceProvider;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by koval on 04.01.2017.
 */
public class ContextDataSourceProvider implements DataSourceProvider {

    private static final Logger LOG = Logger.getLogger(ContextDataSourceProvider.class);

    private static final ContextDataSourceProvider instance = new ContextDataSourceProvider();

    public static ContextDataSourceProvider getInstance() {
        return instance;
    }

    public DataSource getDataSource() {
        try {
            return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
        } catch (final NamingException e) {
            LOG.error("Connection pool can not be created", e);
            throw new IllegalStateException(e);
        }
    }
}
