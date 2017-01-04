package com.getprepared.infrastructure.db;

import com.getprepared.infrastructure.db.DataSourceProvider;
import com.getprepared.infrastructure.db.impl.MySqlDataSourceProvider;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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
