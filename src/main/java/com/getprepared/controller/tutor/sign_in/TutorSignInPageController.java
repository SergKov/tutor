package com.getprepared.controller.tutor.sign_in;

import com.getprepared.controller.common.abstract_classes.AbstractSignInController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public class TutorSignInPageController extends AbstractSignInController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession(false) != null) {
            response.sendRedirect(LINKS.TUTOR_QUIZZES);
            return REDIRECT;
        }

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.SIGN_IN, request.getLocale()));
        fillPage(request);
        return PAGES.TUTOR_SIGN_IN;
    }
}
