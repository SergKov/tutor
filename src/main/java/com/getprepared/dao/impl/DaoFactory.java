package com.getprepared.dao.impl;

import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import com.getprepared.infrastructure.data_source.DataSourceProvider;
import com.getprepared.infrastructure.data_source.impl.MySqlDataSourceProvider;
import com.getprepared.infrastructure.template.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.constant.ServerConstants.DAOS;

/**
 * Created by koval on 06.01.2017.
 */
public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private Map<String, AbstractDao> mapping;
    private JdbcTemplate template;

    private DaoFactory() {
        init();
    }

    private void init() {

        mapping = new HashMap<>();
        template = new JdbcTemplate(new TransactionalConnectionProvider(MySqlDataSourceProvider
                .getInstance().getDataSource())); //TODO !!!

        mapping.put(DAOS.ANSWER_DAO, new AnswerDaoImpl(template));
        mapping.put(DAOS.QUESTION_DAO, new QuestionDaoImpl(template));
        mapping.put(DAOS.QUIZ_DAO, new QuizDaoImpl(template));
        mapping.put(DAOS.USER_DAO, new UserDaoImpl(template));
        mapping.put(DAOS.RESULT_DAO, new ResultDaoImpl(template));
        mapping.put(DAOS.QUESTION_HISTORY_DAO, new QuestionHistoryDaoImpl(template));
        mapping.put(DAOS.CHOSEN_ANSWER_DAO, new ChosenChosenAnswerDaoImpl(template));
    }

    public <T> T getDao(final String name, final Class<T> clazz) {
        final AbstractDao dao = mapping.get(name);
        return clazz.cast(dao);
    }
}
