package com.getprepared.controller.factory;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.controller.Controller;
import com.getprepared.infrastructure.BeanFactory;
import com.getprepared.infrastructure.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.constant.PageConstants.FORMS;

/**
 * Created by koval on 14.01.2017.
 */
public class ControllerFactory {

    private static final ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance() {
        return instance;
    }

    private final BeanFactory beanFactory = new ApplicationContext();

    private Map<String, Controller> mapping;

    private ControllerFactory() {
        init();
    }

    private void init() {

        mapping = new HashMap<>();

        mapping.put(LINKS.STUDENT_SIGN_IN, beanFactory.getBean("studentSignInPage", Controller.class));
        mapping.put(LINKS.NOT_FOUND, beanFactory.getBean("pageNotFound", Controller.class));
        mapping.put(LINKS.SIGN_UP, beanFactory.getBean("signUpPage", Controller.class));
        mapping.put(LINKS.STUDENT_HOME_PAGE, beanFactory.getBean("studentHomePage", Controller.class));
        mapping.put(LINKS.STUDENT_TEST, beanFactory.getBean("studentChangeQuestion", Controller.class));
        mapping.put(LINKS.STUDENT_GET_RESULT, beanFactory.getBean("studentResultPage", Controller.class));
        mapping.put(LINKS.TUTOR_SIGN_IN, beanFactory.getBean("tutorSignInPage", Controller.class));
        mapping.put(LINKS.TUTOR_QUIZZES, beanFactory.getBean("quizPage", Controller.class));
        mapping.put(LINKS.TUTOR_ADD_QUIZ, beanFactory.getBean("quizPageAdd", Controller.class));
        mapping.put(LINKS.TUTOR_QUESTIONS, beanFactory.getBean("questionsPage", Controller.class));
        mapping.put(LINKS.TUTOR_ADD_QUESTION, beanFactory.getBean("questionAddPage", Controller.class));
        mapping.put(LINKS.SIGN_OUT, beanFactory.getBean("signOut", Controller.class));

        mapping.put(FORMS.STUDENT_SIGN_IN, beanFactory.getBean("studentSignIn", Controller.class));
        mapping.put(FORMS.SIGN_UP, beanFactory.getBean("studentSignUp", Controller.class));
        mapping.put(FORMS.STUDENT_START_TEST, beanFactory.getBean("studentStartTest", Controller.class));
        mapping.put(FORMS.STUDENT_SAVE_ANSWER, beanFactory.getBean("studentSaveAnswer", Controller.class));
        mapping.put(FORMS.STUDENT_END_TEST, beanFactory.getBean("studentEndTest", Controller.class));
        mapping.put(FORMS.TUTOR_SIGN_IN, beanFactory.getBean("tutorSignIn", Controller.class));
        mapping.put(FORMS.TUTOR_ADD_QUIZ, beanFactory.getBean("tutorAddQuiz", Controller.class));
        mapping.put(FORMS.TUTOR_REMOVE_QUIZ, beanFactory.getBean("tutorQuizRemove", Controller.class));
        mapping.put(FORMS.TUTOR_ADD_QUESTION, beanFactory.getBean("tutorQuestionAdd", Controller.class));
        mapping.put(FORMS.TUTOR_REMOVE_QUESTION, beanFactory.getBean("tutorQuestionRemove", Controller.class));
    }

    public Controller getController(final String key) {
        return mapping.get(key);
    }
}
