package com.getprepared.dao.impl;

import com.getprepared.database.DataSourceFactory;
import com.getprepared.database.TransactionalConnectionProvider;
import com.getprepared.database.template.JdbcTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;

import static org.mockito.Mockito.doReturn;

/**
 * Created by koval on 01.02.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerDaoImplTest {

    @Spy
    private final TransactionalConnectionProvider provider = new TransactionalConnectionProvider();

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new TransactionalConnectionProvider());

    private AnswerDaoImpl answerDao = new AnswerDaoImpl(jdbcTemplate);

    private DataSource dataSource = DataSourceFactory.getInstance().getTestDataSource();

    @Before
    public void setUp()  {
        doReturn(dataSource).when(provider).getDataSource();
    }

    @Test
    public void requireSaveAnswer() {
//        answerDao.save(new Answer());
    }
}