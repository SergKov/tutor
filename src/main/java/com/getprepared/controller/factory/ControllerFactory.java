package com.getprepared.controller.factory;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.Controller;
import com.getprepared.controller.common.PageNotFoundController;
import com.getprepared.controller.common.sign_up.SignUpController;
import com.getprepared.controller.common.sign_up.SignUpPageController;
import com.getprepared.controller.student.home_page.StudentHomePageController;
import com.getprepared.controller.student.home_page.StudentHomePagePageController;
import com.getprepared.controller.student.sign_in.StudentSignInController;
import com.getprepared.controller.student.sign_in.StudentSignInPageController;
import com.getprepared.controller.tutor.sign_in.TutorSignInController;
import com.getprepared.controller.tutor.sign_in.TutorSignInPageController;

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

        mapping.put(FORMS.STUDENT_SIGN_IN, new StudentSignInController());
        mapping.put(FORMS.SIGN_UP, new SignUpController());
        mapping.put(FORMS.STUDENT_HOME_PAGE, new StudentHomePageController());
        mapping.put(FORMS.TUTOR_SIGN_IN, new TutorSignInController());

        mapping.values().forEach(AbstractController::init);
    }

    public Controller getController(final String key) {
        return mapping.get(key);
    }
}
