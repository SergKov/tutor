package com.getprepared.infrastructure;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by koval on 02.01.2017.
 */
public class ConnectionFactory {

    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class);

    private static final String INIT_DS = "A connection pool is created";

    private static final ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        instance.init();
        return instance;
    }

    private DataSource ds;

    private ConnectionFactory() { }

    private void init() {
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
            LOG.debug(INIT_DS);
        } catch (final NamingException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
