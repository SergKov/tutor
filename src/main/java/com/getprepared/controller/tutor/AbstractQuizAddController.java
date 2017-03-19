package com.getprepared.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.util.Messages;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZ_NAME_REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractQuizAddController implements Controller {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(NAMES.ADD_QUIZ, request.getLocale()));
        request.setAttribute(QUIZ_NAME_REGEX, REGEX.QUIZ_NAME);
    }
}
