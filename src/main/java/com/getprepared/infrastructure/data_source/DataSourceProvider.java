package com.getprepared.infrastructure.data_source;

import javax.sql.DataSource;

/**
 * Created by koval on 04.01.2017.
 */
public interface DataSourceProvider {

    DataSource getDataSource();
}
