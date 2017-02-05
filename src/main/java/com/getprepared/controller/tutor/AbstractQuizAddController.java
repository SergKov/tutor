package com.getprepared.controller.tutor;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZ_NAME_REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractQuizAddController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUIZ, request.getLocale()));
        request.setAttribute(QUIZ_NAME_REGEX, REGEX.QUIZ_NAME);
    }
}
