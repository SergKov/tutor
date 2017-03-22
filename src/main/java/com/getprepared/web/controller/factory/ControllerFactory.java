package com.getprepared.web.controller.factory;

import com.getprepared.context.ApplicationContext;
import com.getprepared.context.BeanFactory;
import com.getprepared.web.constant.PageConstants.LINKS;
import com.getprepared.web.controller.Command;

import java.util.HashMap;
import java.util.Map;

import static com.getprepared.web.constant.PageConstants.FORMS;

/**
 * Created by koval on 14.01.2017.
 */
public class ControllerFactory {

    private static final ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance() {
        return instance;
    }

    private final BeanFactory beanFactory = new ApplicationContext();

    private Map<String, Command> mapping;

    private ControllerFactory() {
        init();
    }

    private void init() {

        mapping = new HashMap<>();

        mapping.put(LINKS.STUDENT_SIGN_IN, beanFactory.getBean("studentSignInPage", Command.class));
        mapping.put(LINKS.NOT_FOUND, beanFactory.getBean("pageNotFound", Command.class));
        mapping.put(LINKS.SIGN_UP, beanFactory.getBean("signUpPage", Command.class));
        mapping.put(LINKS.STUDENT_HOME_PAGE, beanFactory.getBean("studentHomePage", Command.class));
        mapping.put(LINKS.STUDENT_TEST, beanFactory.getBean("studentChangeQuestion", Command.class));
        mapping.put(LINKS.STUDENT_GET_RESULT, beanFactory.getBean("studentResultPage", Command.class));
        mapping.put(LINKS.TUTOR_SIGN_IN, beanFactory.getBean("tutorSignInPage", Command.class));
        mapping.put(LINKS.TUTOR_QUIZZES, beanFactory.getBean("quizPage", Command.class));
        mapping.put(LINKS.TUTOR_ADD_QUIZ, beanFactory.getBean("quizPageAdd", Command.class));
        mapping.put(LINKS.TUTOR_QUESTIONS, beanFactory.getBean("questionsPage", Command.class));
        mapping.put(LINKS.TUTOR_ADD_QUESTION, beanFactory.getBean("questionAddPage", Command.class));
        mapping.put(LINKS.SIGN_OUT, beanFactory.getBean("signOut", Command.class));

        mapping.put(FORMS.STUDENT_SIGN_IN, beanFactory.getBean("studentSignIn", Command.class));
        mapping.put(FORMS.SIGN_UP, beanFactory.getBean("signUp", Command.class));
        mapping.put(FORMS.STUDENT_START_TEST, beanFactory.getBean("studentStartTest", Command.class));
        mapping.put(FORMS.STUDENT_SAVE_ANSWER, beanFactory.getBean("studentSaveAnswer", Command.class));
        mapping.put(FORMS.STUDENT_END_TEST, beanFactory.getBean("studentEndTest", Command.class));
        mapping.put(FORMS.TUTOR_SIGN_IN, beanFactory.getBean("tutorSignIn", Command.class));
        mapping.put(FORMS.TUTOR_ADD_QUIZ, beanFactory.getBean("quizAdd", Command.class));
        mapping.put(FORMS.TUTOR_REMOVE_QUIZ, beanFactory.getBean("quizRemove", Command.class));
        mapping.put(FORMS.TUTOR_ADD_QUESTION, beanFactory.getBean("questionAdd", Command.class));
        mapping.put(FORMS.TUTOR_REMOVE_QUESTION, beanFactory.getBean("questionRemove", Command.class));
    }

    public Command getCommand(final String key) {
        return mapping.get(key);
    }
}
