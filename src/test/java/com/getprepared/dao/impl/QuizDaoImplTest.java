package com.getprepared.dao.impl;

import com.getprepared.database.DataSourceFactory;
import com.getprepared.database.TransactionalConnectionProvider;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

/**
 * Created by koval on 02.02.2017.
 */
public class QuizDaoImplTest {

    @Spy
    private final TransactionalConnectionProvider provider = new TransactionalConnectionProvider();

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new TransactionalConnectionProvider());

    private QuizDaoImpl quizDao = new QuizDaoImpl(jdbcTemplate);

    private DataSource dataSource = DataSourceFactory.getInstance().getTestDataSource();

    @Before
    public void setUp()  {
        doReturn(dataSource).when(provider).getDataSource();
    }

    @Test
    public void requireSaveQuiz() throws Exception {

        final Quiz quiz = initQuiz("Java");
        quizDao.save(quiz);

        final List<Quiz> quizzes = quizDao.findAll();
        assertThat(quizzes, contains(quiz));
    }

    private Quiz initQuiz(final String name) {
        final Quiz quiz = new Quiz();
        quiz.setName(name);
        return quiz;
    }
}