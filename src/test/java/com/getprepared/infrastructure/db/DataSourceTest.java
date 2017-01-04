package com.getprepared.infrastructure.db;

import com.getprepared.infrastructure.db.DataSourceProvider;
import com.getprepared.infrastructure.db.impl.MySqlDataSourceProvider;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by koval on 03.01.2017.
 */
public class DataSourceTest {

    private DataSourceProvider provider;

    @Before
    public void setUp() {
        provider = MySqlDataSourceProvider.getInstance();
    }

    @Test
    public void requireDataSource() throws SQLException {
        final DataSource ds = provider.getDataSource();
        assertNotNull(ds);
    }
}
