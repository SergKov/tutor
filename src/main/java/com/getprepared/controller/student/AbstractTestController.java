package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants.NAMES;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZ;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public abstract class AbstractTestController extends AbstractController {

    public void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.TEST, request.getLocale()));
    }
}
