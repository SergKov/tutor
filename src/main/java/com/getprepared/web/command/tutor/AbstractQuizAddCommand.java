package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.QUIZ_NAME_REGEX;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractQuizAddCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.ADD_QUIZ, request.getLocale()));
        request.setAttribute(QUIZ_NAME_REGEX, REGEX.QUIZ_NAME);
    }
}
