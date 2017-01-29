package com.getprepared.controller.factory;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.Controller;
import com.getprepared.controller.common.PageNotFoundController;
import com.getprepared.controller.common.SignOutController;
import com.getprepared.controller.common.SignUpController;
import com.getprepared.controller.common.SignUpPageController;
import com.getprepared.controller.student.StudentHomePageController;
import com.getprepared.controller.student.StudentHomePagePageController;
import com.getprepared.controller.student.StudentSignInController;
import com.getprepared.controller.student.StudentSignInPageController;
import com.getprepared.controller.tutor.*;

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

    private Map<String, AbstractController> mapping;

    private ControllerFactory() {
        init();
    }

    private void init() {

        mapping = new HashMap<>();

        mapping.put(LINKS.STUDENT_SIGN_IN, new StudentSignInPageController());
        mapping.put(LINKS.NOT_FOUND, new PageNotFoundController());
        mapping.put(LINKS.SIGN_UP, new SignUpPageController());
        mapping.put(LINKS.STUDENT_HOME_PAGE, new StudentHomePagePageController());
        mapping.put(LINKS.TUTOR_SIGN_IN, new TutorSignInPageController());
        mapping.put(LINKS.TUTOR_QUIZZES, new QuizPageController());
        mapping.put(LINKS.ADD_QUIZ, new QuizPageAddController());
        mapping.put(LINKS.TUTOR_QUESTIONS, new QuestionsPageController());
        mapping.put(LINKS.ADD_QUESTION, new QuestionAddPageController());
        mapping.put(LINKS.SIGN_OUT, new SignOutController());

        mapping.put(FORMS.STUDENT_SIGN_IN, new StudentSignInController());
        mapping.put(FORMS.SIGN_UP, new SignUpController());
        mapping.put(FORMS.STUDENT_HOME_PAGE, new StudentHomePageController());
        mapping.put(FORMS.TUTOR_SIGN_IN, new TutorSignInController());
        mapping.put(FORMS.ADD_QUIZ, new QuizAddController());
        mapping.put(FORMS.REMOVE_QUIZ, new QuizRemoveController());
        mapping.put(FORMS.ADD_QUESTION, new QuestionAddController());
        mapping.put(FORMS.REMOVE_QUESTION, new QuestionRemoveController());
        mapping.put(FORMS.QUIZ_QUESTIONS, new QuizQuestionsController());

        mapping.values().forEach(AbstractController::init);
    }

    public Controller getController(final String key) {
        return mapping.get(key);
    }
}
