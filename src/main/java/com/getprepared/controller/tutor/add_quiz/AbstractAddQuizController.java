package com.getprepared.controller.tutor.add_quiz;

import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.LINKS.ADD_QUIZ;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractAddQuizController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUIZ, request.getLocale()));
        request.setAttribute(ADD_QUIZ, REGEX.QUIZ_NAME);
    }
}
