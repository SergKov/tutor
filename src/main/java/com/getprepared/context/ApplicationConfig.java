package com.getprepared.context;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Configuration;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by koval on 07.03.2017.
 */
@Configuration
public class ApplicationConfig {

    private static final Logger LOG = Logger.getLogger(ApplicationConfig.class);

    @Bean
    public DataSource getDataSource() {
        try {
            return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tutor");
        } catch (final NamingException e) {
            LOG.fatal("Failed to get DataSource", e);
            throw new IllegalStateException(e);
        }
    }
}
