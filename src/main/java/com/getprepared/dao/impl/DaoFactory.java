package com.getprepared.dao.impl;

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

    private DaoFactory() {
        init();
    }

    private void init() {

        mapping = new HashMap<>();

        mapping.put(DAOS.ANSWER_DAO, new AnswerDaoImpl());
        mapping.put(DAOS.QUESTION_DAO, new QuestionDaoImpl());
        mapping.put(DAOS.QUIZ_DAO, new QuizDaoImpl());
        mapping.put(DAOS.USER_DAO, new UserDaoImpl());
        mapping.put(DAOS.RESULT_DAO, new ResultDaoImpl());
        mapping.put(DAOS.QUESTION_HISTORY_DAO, new QuestionHistoryDaoImpl());
        mapping.put(DAOS.CHOSEN_ANSWER_DAO, new ChosenChosenAnswerDaoImpl());
    }

    public <T> T getDao(final String name, final Class<T> clazz) {
        final AbstractDao dao = mapping.get(name);
        return clazz.cast(dao);
    }
}
