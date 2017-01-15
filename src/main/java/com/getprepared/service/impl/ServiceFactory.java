package com.getprepared.service.impl;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.constant.ServerConstants.SERVICES;

/**
 * Created by koval on 14.01.2017.
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private Map<String, AbstractService> mapping;

    private void init() {

        mapping = new HashMap<>();

        mapping.put(SERVICES.ANSWER_SERVICE, new AnswerServiceImpl());
        mapping.put(SERVICES.QUESTION_SERVICE, new QuestionServiceImpl());
        mapping.put(SERVICES.SPECIALITY_SERVICE, new SpecialityServiceImpl());
        mapping.put(SERVICES.QUIZ_SERVICE, new QuizServiceImpl());
        mapping.put(SERVICES.RESULT_SERVICE, new ResultServiceImpl());
        mapping.put(SERVICES.USER_SERVICE, new UserServiceImpl());

        mapping.values().forEach(AbstractService::init);
    }

    private ServiceFactory() {
        init();
    }

    public <T> T getService(final String serviceName, final Class<T> clazz) {
        return clazz.cast(mapping.get(serviceName));
    }
}
