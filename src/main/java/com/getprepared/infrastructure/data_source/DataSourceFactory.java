package com.getprepared.infrastructure.data_source;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by koval on 12.01.2017.
 */
public class DataSourceFactory {

    private static final Logger LOG = Logger.getLogger(DataSourceFactory.class);

    private static final DataSourceFactory instance = new DataSourceFactory();

    public static DataSourceFactory getInstance() {
        return instance;
    }

    public DataSource getDataSource() {
        final MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/tutor_test");
        ds.setUser("root");
        ds.setPassword("root");
        return ds;

//        try {
//            return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
//        } catch (final NamingException e) {
//            LOG.fatal("Failed to get DataSource", e);
//            throw new IllegalStateException(e);
//        }
    }
}
