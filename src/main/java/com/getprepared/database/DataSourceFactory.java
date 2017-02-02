package com.getprepared.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.utils.impl.PropertyUtils.initProp;

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
        try {
            return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
        } catch (final NamingException e) {
            LOG.fatal("Failed to get DataSource", e);
            throw new IllegalStateException(e);
        }
    }

    public DataSource getTestDataSource() {
        final MysqlDataSource source = new MysqlDataSource();
        source.setURL(initProp(FILES_NAMES.DS_SETTING).getProperty(KEYS.URL));
        source.setUser(initProp(FILES_NAMES.DS_SETTING).getProperty(KEYS.USER));
        source.setPassword(initProp(FILES_NAMES.DS_SETTING).getProperty(KEYS.PASSWORD));
        return source;
    }
}
