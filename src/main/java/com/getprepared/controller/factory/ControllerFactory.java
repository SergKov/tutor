package com.getprepared.controller.factory;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.Controller;
import com.getprepared.controller.command.student.home_page.HomePageController;
import com.getprepared.controller.command.student.home_page.StudentSignInController;

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

        mapping.put(LINKS.HOME_PAGE, new HomePageController());

        mapping.put(FORMS.STUDENT_SIGN_IN, new StudentSignInController());

        mapping.values().forEach(AbstractController::init);
    }

    public Controller getController(final String key) {
        return mapping.get(key);
    }
}
