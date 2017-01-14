package com.getprepared.dao.impl;

import com.getprepared.infrastructure.connection.TransactionalConnectionProvider;
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
        template = new JdbcTemplate(getTransactionalConnectionProvider());

        mapping.put(DAOS.ANSWER_DAO, new AnswerDaoImpl(template));
        mapping.put(DAOS.QUESTION_DAO, new QuestionDaoImpl(template));
        mapping.put(DAOS.QUIZ_DAO, new QuizDaoImpl(template));
        mapping.put(DAOS.SPECIALITY_DAO, new SpecialityDaoImpl(template));
        mapping.put(DAOS.USER_DAO, new UserDaoImpl(template));
        mapping.put(DAOS.RESULT_DAO, new ResultDaoImpl(template));
    }

    public <T> T getDao(final String name, final Class<T> clazz) {
        final AbstractDao dao = mapping.get(name);
        return clazz.cast(dao);
    }

    private TransactionalConnectionProvider getTransactionalConnectionProvider() {
        return TransactionalConnectionProvider.getInstance();
    }
}
