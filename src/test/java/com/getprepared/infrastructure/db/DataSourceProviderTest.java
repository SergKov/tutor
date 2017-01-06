package com.getprepared.infrastructure.db;

import com.getprepared.infrastructure.data_source.DataSourceProvider;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by koval on 05.01.2017.
 */
public class DataSourceProviderTest {

    private DataSourceProvider dataSourceProvider;

    @Test
    public void requireDataSourceProvider() {
        dataSourceProvider = MySqlDataSourceProvider.getInstance();
        assertNotNull(dataSourceProvider);
    }
}
