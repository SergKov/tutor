package com.getprepared.infrastructure.data_source;

import com.getprepared.utils.PropertyUtils;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;

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

        final String ds = getPropertyUtils().getValue(FILES_NAMES.DS_SETTING, KEYS.DS);

        switch (ds) {
            case "base" : return getBaseDataSource();
            case "test" : return getTestDataSource();
            default:
                final String unsupportedDataSource = String.format("This DataSource %s is unsupported", ds);
                LOG.fatal(unsupportedDataSource);
                throw new IllegalArgumentException(unsupportedDataSource);
        }
    }

    public DataSource getBaseDataSource() {
        try {
            return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
        } catch (final NamingException e) {
            LOG.fatal("Failed to get DataSource", e);
            throw new IllegalStateException(e);
        }
    }

    public DataSource getTestDataSource() {

        final MysqlDataSource ds = new MysqlDataSource();

        final String url = getPropertyUtils().getValue(FILES_NAMES.DS_TEST_SETTING, KEYS.URL);
        ds.setURL(url);

        final String user = getPropertyUtils().getValue(FILES_NAMES.DS_TEST_SETTING, KEYS.USER);
        ds.setUser(user);

        final String password = getPropertyUtils().getValue(FILES_NAMES.DS_TEST_SETTING, KEYS.PASSWORD);
        ds.setPassword(password);

        return ds;
    }

    private PropertyUtils getPropertyUtils() {
        return PropertyUtils.getInstance();
    }
}
