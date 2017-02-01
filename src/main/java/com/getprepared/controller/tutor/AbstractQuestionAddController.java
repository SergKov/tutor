package com.getprepared.controller.tutor;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.AnswerType;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 26.01.2017.
 */
public abstract class AbstractQuestionAddController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.ADD_QUESTION, request.getLocale()));
        request.setAttribute(ANSWER_TYPES, AnswerType.values());
        request.setAttribute(ANSWER_TYPE_REGEX, REGEX.ANSWER_TYPE);
    }
}
